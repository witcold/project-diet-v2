_.templateSettings = {
	interpolate: /\{\{(.+?)\}\}/g
};

var DateView = Backbone.View.extend({
	el: $('#date'),
	template: _.template($("#date-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template({}));
	}
});

var dateView = new DateView();

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
		var weights = new WeightList();
		weights.fetch();
		var weightsListView = new WeightListVeiw({ collection: weights });
	},
	month: function (fromDate, toDate) {
		var weights = new WeightList();
		weights.fromDate = fromDate;
		weights.toDate = toDate;
		weights.fetch({ reset: true });
		var weightsListView = new WeightListVeiw({ collection: weights });
	}
});

var app = new AppRouter();

Backbone.history.start();
