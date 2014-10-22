_.templateSettings = {
	evaluate: /\(@([\s\S]+?)@\)/g,
	interpolate: /\(@=(.+?)@\)/g,
	escape: /\(@-([\s\S]+?)@\)/g
};

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
	}
});

var weights = new WeightList();

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
	},
	current: function () {
		this.render(new Date().toLocaleFormat("%Y-%m"));
	},
	month: function (month) {
		this.render(month);
	}
});

var app = new AppRouter();

Backbone.history.start();

$('#datetimepicker').datetimepicker({
	format: 'YYYY.MM.DD',
	pickTime: false,
	useStrict: true
});

var datetimepicker = $('#datetimepicker').data("DateTimePicker");
var weightform = $('#weightForm');

$(function plot() {
	var weightChart = plotEmptyChart('#placeholder', {
		tooltip: {
			valueSuffix: ' <spring:message code="weight.measure"/>'
		}
	});
	$.get('weight/raw', function(result) {
		weightChart.addSeries({
			name: '<spring:message code="label.weight"/>',
			data: process(result, 'date', 'weight')
		});
	});
});

function deleteWeight(date) {
	if (confirm('<spring:message code="form.confirm"/>'))
		$.post('weight/delete', {'date': date}, function(result) {
			location.reload();
		});
};

function editForm(date, weight) {
	datetimepicker.setDate(new Date(date));
	weightform.find('#datetimepicker .input-group-addon').hide();
	weightform.find('.date').removeClass('input-group');
	$('#weight').val(weight);
	$('#weightModal').modal('show');
};

$('#weightModal').on('hidden.bs.modal', function (e) {
	weightform.trigger('reset');
	weightform.find('.date').addClass('input-group');
	weightform.find('.input-group-addon').show();
});

function validateDate(event) {
	var date = weightform.find('#datetimepicker input').val();
	return !!date;
}

function sendForm (event) {
	if (!validateDate(event)) {
		return false;
	}
	event.preventDefault();

	var weight = {
		date: weightform.find("input[name='date']").val(),
		weight: weightform.find("input[name='weight']").val()
	};

	var posting = $.post('weight/add', weight);

	posting.done(function (data) {
		var content = $(data).find('#content');
		weights.add(weight);
	});
	$('#weightModal').modal('hide');
}
