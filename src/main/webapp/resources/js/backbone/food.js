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
		this.$el.html(this.model.get("id"));
		return this;
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
