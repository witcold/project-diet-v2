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

var ContainerView  = Backbone.View.extend({
	el: $(".container"),
	template: _.template($("#container-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "home"
	},
	home: function () {
		var containerView = new ContainerView();
		var titleView = new TitleView();
	}
});

var app = new AppRouter();

Backbone.history.start();
