var ContainerView  = Backbone.View.extend({
	el: $(".container"),
	template: _.template($("#container-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(this.messages));
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "home"
	},
	render: function (messages) {
		var containerView = new ContainerView(messages);
	},
	home: function () {
		$.getScript('messages', function () {
			this.render(messages);
		})
	}
});

var app = new AppRouter();

Backbone.history.start();
