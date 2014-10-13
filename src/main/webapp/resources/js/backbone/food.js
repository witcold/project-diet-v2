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
		_.each(this.collection.models, function (category) {
			this.$el.append(new CategoriesLisItemtVeiw({model: category}).render().el);
		})
	}
});

var categories = new CategoriesListVeiw({
	collection: categories
});

var CategoriesLisItemtVeiw = Backbone.View.extend({
	tagName: 'a',
	className: 'list-group-item',
	render: function () {
		this.$el.html(this.model.get("name"));
	}
})

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "food"
	},
	food: function () {
	}
});

var app = new AppRouter();

Backbone.history.start();
