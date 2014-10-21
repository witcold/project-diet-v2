_.templateSettings = {
	evaluate: /\(@([\s\S]+?)@\)/g,
	interpolate: /\(@=(.+?)@\)/g,
	escape: /\(@-([\s\S]+?)@\)/g
};

var DateView = Backbone.View.extend({
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

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "current",
		":fromDate/:toDate": "month"
	},
	current: function () {
		var now = new Date();
		now.setHours(0,0,0,0);
		now.setDate(1);
		var prevFrom = new Date(now);
		prevFrom.setMonth(now.getMonth() - 1);
		var nextFrom = new Date(now);
		nextFrom.setMonth(now.getMonth() + 1);
		var nextTo = new Date(now);
		nextTo.setMonth(now.getMonth() + 2);
		var dateView = new DateView({
			model: {
				now: now,
				prevFrom: prevFrom.toLocaleFormat("%Y-%m-%d"),
				prevTo: now.toLocaleFormat("%Y-%m-%d"),
				nextFrom: nextFrom.toLocaleFormat("%Y-%m-%d"),
				nextTo: nextTo.toLocaleFormat("%Y-%m-%d")
			}
		});
		var weights = new WeightList();
		weights.fromDate = now;
		weights.toDate = nextFrom;
		weights.fetch();
		var weightsListView = new WeightListVeiw({ collection: weights });
	},
	month: function (fromDate, toDate) {
		fromDate = new Date(Date.parse(fromDate));
		toDate = new Date(Date.parse(toDate));
		var now = fromDate;
		var prevFrom = new Date(now);
		prevFrom.setMonth(now.getMonth() - 1);
		var nextFrom = new Date(now);
		nextFrom.setMonth(now.getMonth() + 1);
		var nextTo = new Date(now);
		nextTo.setMonth(now.getMonth() + 2);
		var dateView = new DateView({
			model: {
				now: now,
				prevFrom: prevFrom.toLocaleFormat("%Y-%m-%d"),
				prevTo: now.toLocaleFormat("%Y-%m-%d"),
				nextFrom: nextFrom.toLocaleFormat("%Y-%m-%d"),
				nextTo: nextTo.toLocaleFormat("%Y-%m-%d")
			}
		});
		var weights = new WeightList();
		weights.fromDate = fromDate;
		weights.toDate = toDate;
		weights.fetch({ reset: true });
		var weightsListView = new WeightListVeiw({ collection: weights });
	}
});

var app = new AppRouter();

Backbone.history.start();
