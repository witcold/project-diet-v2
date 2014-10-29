var TitleView = Backbone.View.extend({
	el: $("title"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(messages.i18n['label.weight']);
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
		this.$el.find('#container-label').html(messages.i18n['label.weight']);
		this.$el.find('#table-header').html(this.template(messages));
		this.$el.find('#add-weight').html(messages.i18n['weight.add']);
	}
});

var containerView = new ContainerView();

var ModalView = Backbone.View.extend({
	el: $(".modal-content"),
	template: _.template($("#modal-template").html()),
	initialize: function () {
		this.render();
		datetimepicker = $('#datetimepicker').datetimepicker({
			format: 'YYYY.MM.DD',
			pickTime: false,
			useStrict: true
		}).data("DateTimePicker");
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var modalView = new ModalView();

var DatePagerView = Backbone.View.extend({
	el: $('#date'),
	template: _.template($("#weight-month-pager-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(this.model));
	}
});

var WeightListVeiw = Backbone.View.extend({
	el: $("#weight-table"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (weight) {
			var view = new WeightListItemView({model: weight});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var WeightListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#weight-tr-template").html()),
	initialize: function () {
		this.listenTo(this.model, 'change', this.render);
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
		var self = this;
		this.$el.find('#edit').on('click', function () {
			editForm(self.model.get('date'), self.model.get('weight'));
		});
		this.$el.find('#delete').on('click', function () {
			deleteWeight(self.model.get('date'));
			weights.remove(self.model);
		});
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "current",
		":month": "month"
	},
	render: function (date) {
		var now = new Date(date);
		var toDate = new Date(now);
		toDate.setMonth(toDate.getMonth() + 1);
		toDate.setDate(0);
		var prev = new Date(date);
		prev.setMonth(prev.getMonth() - 1);
		var next = new Date(date);
		next.setMonth(next.getMonth() + 1);
		var datePagerView = new DatePagerView({
			model: {
				now: now,
				prev: prev.toLocaleFormat("%Y-%m"),
				next: next.toLocaleFormat("%Y-%m"),
			}
		});
		weights.fromDate = now;
		weights.toDate = toDate;
		weights.fetch({ reset: true });
		var weightsListView = new WeightListVeiw({ collection: weights });
		plot();
	},
	current: function () {
		this.render(new Date().toLocaleFormat("%Y-%m"));
	},
	month: function (month) {
		this.render(month);
	}
});

var datetimepicker;
var weightform = $('#weightForm');

var app = new AppRouter();
Backbone.history.start();

function deleteWeight(date) {
	if (confirm(messages.i18n['form.confirm']))
		$.post('weights/delete', {'date': new Date(date).toLocaleFormat("%Y-%m-%d")});
};

function plot() {
	var weightChart = plotEmptyChart('#placeholder', {
		tooltip: {
			valueSuffix: ' ' + messages.i18n['weight.measure']
		}
	});
	$.get('weights', function(result) {
		weightChart.addSeries({
			name: messages.i18n['label.weight'],
			data: process(result, 'date', 'weight')
		});
	});
};

function editForm(date, weight) {
	datetimepicker.setDate(new Date(date));
	weightform.find('#datetimepicker .input-group-addon').hide();
	weightform.find('.date').removeClass('input-group');
	$('#weight').val(weight);
	$('#weightModal').modal('show');
};

$('#weightModal').on('hidden.bs.modal', function () {
	weightform.trigger('reset');
	weightform.find('.date').addClass('input-group');
	weightform.find('.input-group-addon').show();
});

function validateDate() {
	var date = weightform.find('#datetimepicker input').val();
	return !!date;
}

function sendForm (event) {
	event.preventDefault();
	if (!validateDate()) {
		datetimepicker.show(event);
		return;
	}

	var weight = {
		date: new Date(weightform.find("input[name='date']").val()).toLocaleFormat("%Y-%m-%d"),
		weight: weightform.find("input[name='weight']").val()
	};

	var posting = $.post('weights/add', weight);

	posting.done(function () {
		weights.add(weight);
	});
	$('#weightModal').modal('hide');
}
