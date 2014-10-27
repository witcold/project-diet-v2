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
	}
});

var app = new AppRouter();

Backbone.history.start();
