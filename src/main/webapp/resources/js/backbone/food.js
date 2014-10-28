var TitleView = Backbone.View.extend({
	el: $("title"),
	template: _.template($("#title-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var titleView = new TitleView();

var ContainerTitleView = Backbone.View.extend({
	el: $("#container-label"),
	initialize: function () {
		this.render();
	},
	render: function (categoryName) {
		this.$el.html(messages.i18n['label.food']);
		if (categoryName) {
			this.$el.append("<small> " + categoryName + "</small>");
		}
	}
});

var containerTitleView = new ContainerTitleView();

var TableTitleView = Backbone.View.extend({
	el: $("#table-header"),
	template: _.template($("#table-header-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

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

var categoriesView = new CategoriesListVeiw({
	collection: categories
});

var CategoriesLisItemtVeiw = Backbone.View.extend({
	tagName: 'a',
	className: 'list-group-item',
	attributes: function () {
		return { href: 'food#category/' + this.model.get("id") };
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
			var view = new FoodsListItemView({model: food});
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
		"": "all",
		"category/:id": "category"
	},
	render: function (category) {
		var tableTitleView = new TableTitleView();
		var foods = new FoodList();
		foods.category = category;
		foods.fetch({ reset: true });
		var foodsListView = new FoodsListView({ collection: foods });
	},
	all: function () {
		this.render();
	},
	category: function (id) {
		containerTitleView.render(categories.get(id).get("name"));
		this.render(id);
	}
});

var app = new AppRouter();

Backbone.history.start();
