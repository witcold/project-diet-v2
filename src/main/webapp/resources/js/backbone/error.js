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

var containerView = new ContainerView();
