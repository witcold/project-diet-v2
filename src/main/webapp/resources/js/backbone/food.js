_.templateSettings = {
	interpolate: /\{\{(.+?)\}\}/g
};

var CategoriesListVeiw = Backbone.View.extend({
	el: $(".categories"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
		this.collection.fetch();
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (category) {
			var view = new CategoriesLisItemtVeiw({model: category});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var categories = new CategoriesListVeiw({
	collection: categories
});

var CategoriesLisItemtVeiw = Backbone.View.extend({
	tagName: 'a',
	className: 'list-group-item',
	attributes: function () {
		return { href: 'food?category=' + this.model.get("id") };
	},
	render: function () {
		this.$el.html(this.model.get("name"));
	}
});

var FoodsListView = Backbone.View.extend({
	el: $('#food-table'),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (food) {
			var view = new FoodsListView({model: food});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var FoodsListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#food-tr-template").html()),
	initialize: function () {
		this.listenTo(this.model, 'change', this.render);
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "food"
	},
	food: function () {
		var foods = new FoodList();
		foods.fetch();
		var foodsListView = new FoodsListView({ collection: foods });
	}
});

var app = new AppRouter();

Backbone.history.start();
