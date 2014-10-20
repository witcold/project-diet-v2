_.templateSettings = {
	evaluate: /\(@([\s\S]+?)@\)/g,
	interpolate: /\(@=(.+?)@\)/g,
	escape: /\(@-([\s\S]+?)@\)/g
};

var GoalListVeiw = Backbone.View.extend({
	el: $("#goal-table"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (weight) {
			var view = new GoalListItemView({model: weight});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var GoalListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#goal-tr-template").html()),
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
	},
	current: function () {
		var goals = new GoalList();
		goals.fetch();
		var goalListView = new GoalListVeiw({ collection: goals });
	}
});

var app = new AppRouter();

Backbone.history.start();
