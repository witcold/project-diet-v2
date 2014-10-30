var TitleView = Backbone.View.extend({
	el: $("title"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(messages.i18n['label.diary']);
	}
});

var titleView = new TitleView();

var ContainerView = Backbone.View.extend({
	el: $(".container"),
	template: _.template($("#table-header-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.find('#container-label').html(messages.i18n['label.diary']);
		this.$el.find('#table-header').html(this.template(messages));
		this.$el.find('#add-diary').html(messages.i18n['diary.add']);
	}
});

var containerView = new ContainerView();

var ModalView = Backbone.View.extend({
	el: $(".modal-content"),
	template: _.template($("#modal-template").html()),
	initialize: function () {
		this.render();
		datetimepicker = $('#datetimepicker').datetimepicker({
			format: 'YYYY.MM.DD HH:mm',
			pickDate: false,
			useStrict: true
		}).data("DateTimePicker");
		var engine = new Bloodhound({
			name: 'foods',
			local: [],
			remote: 'foods/search?query=%QUERY',
			datumTokenizer: function(d) {
				return Bloodhound.tokenizers.whitespace(d.name);
			},
			queryTokenizer: Bloodhound.tokenizers.whitespace
		});
		engine.initialize();

		$('.typeahead').typeahead(null, {
			displayKey: 'name',
			source: engine.ttAdapter()
		}).on('typeahead:selected typeahead:autocompleted', function(e, datum) {
			$('#foodId').val(datum.id);
			lastDatum = datum;
		});
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var modalView = new ModalView();

var DateView = Backbone.View.extend({
	el: $('#date'),
	template: _.template($("#diary-day-pager-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(this.model));
	}
});

var DiaryListVeiw = Backbone.View.extend({
	el: $("#diary-table"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (diary) {
			var view = new DiaryListItemView({model: diary});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var TotalCaloriesView = Backbone.View.extend({
	el: $('blockquote.lead'),
	template: _.template($("#diary-total-calories-template").html()),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var sum = this.collection.reduce(function (memo, model) {
			return memo + model.get('food').calories*model.get('weight')*10;
		}, 0);
		this.$el.html(this.template($.extend({total: sum}, messages)));
	}
});

var DiaryListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#diary-tr-template").html()),
	initialize: function () {
		this.listenTo(this.model, 'change', this.render);
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
		var self = this;
		this.$el.find('#edit').on('click', function () {
			lastDatum = self.model.get('food');
			editForm(self.model.get('timestamp'), lastDatum.id, lastDatum.name, self.model.get('weight'));
		});
		this.$el.find('#delete').on('click', function () {
			deleteDiary(self.model.get('food').id, self.model.get('timestamp'));
			diaries.remove(self.model);
		});
	}
});


var diaries = new DiaryList();

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "current",
		":date": "date"
	},
	render: function (date) {
		datetimepicker.setDate(date);
		var prev = new Date(date);
		prev.setDate(date.getDate() - 1);
		var next = new Date(date);
		next.setDate(date.getDate() + 1);
		var dateView = new DateView({
			model: {
				now: date,
				prev: prev.toLocaleFormat("%Y-%m-%d"),
				next: next.toLocaleFormat("%Y-%m-%d"),
			}
		});
		diaries.date = date;
		diaries.fetch();
		var diaryListVeiw = new DiaryListVeiw({ collection: diaries });
		var total = new TotalCaloriesView({ collection: diaries });
		plot();
	},
	current: function () {
		var now = new Date();
		now.setHours(0,0,0,0);
		this.render(now);
	},
	date: function (date) {
		var now = new Date(Date.parse(date));
		this.render(now);
	}
});

var lastDatum;
var datetimepicker;
var diaryform = $('#diaryForm');

var app = new AppRouter();
Backbone.history.start();

function deleteDiary(foodId, timestamp) {
	if (confirm(messages.i18n['form.confirm']))
		$.post('diaries/delete', {'food.id': foodId, 'timestamp': new Date(timestamp).toLocaleFormat("%Y.%m.%d %H:%M")});
};

function plot() {
	var diaryChart = plotEmptyChart('#placeholder', {
		tooltip: {
			valueSuffix: ' ' + messages.i18n['calories.measure']
		}
	});
	$.get('diaries/aggregated', function(result) {
		diaryChart.addSeries({
			name: messages.i18n['diary.calories.total'],
			data: process(result, 'date', 'calories')
		});
	});
};

function editForm(timestamp, foodId, foodName, weight) {
	datetimepicker.setDate(new Date(timestamp));
	diaryform.find('#datetimepicker .input-group-addon').hide();
	diaryform.find('.date').removeClass('input-group');
	diaryform.find('#foodTypeahead').val(foodName);
	diaryform.find('#foodId').val(foodId);
	diaryform.find('#weight').val(weight);
	$('#diaryModal').modal('show');
};

$('#diaryModal').on('hidden.bs.modal', function (e) {
	diaryform.trigger('reset');
	diaryform.find('.date').addClass('input-group');
	diaryform.find('.input-group-addon').show();
});

function validateDate() {
	var date = diaryform.find('#datetimepicker input').val();
	return !!date;
}

function validateForm() {
	var food = diaryform.find('#foodId').val();
	return !!parseInt(food);
}

function sendForm (event) {
	event.preventDefault();
	if (!validateDate()) {
		datetimepicker.show(event);
		return;
	}
	if (!validateForm()) {
		diaryform.find('#foodTypeahead').focus();
		return;
	}

	var diary = {
		timestamp: diaryform.find("input[name='timestamp']").val(),
		weight: diaryform.find("input[name='weight']").val()
	};
	diary['food.id'] = diaryform.find("input[name='food.id']").val();

	var posting = $.post('diaries/add', diary);

	diary.food = {
		name: diaryform.find("#foodTypeahead").val(),
		calories: lastDatum.calories
	};

	posting.done(function () {
		diaries.add(diary);
	});
	$('#diaryModal').modal('hide');
}
