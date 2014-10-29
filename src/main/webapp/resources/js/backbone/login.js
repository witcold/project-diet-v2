var TitleView = Backbone.View.extend({
	el: $("title"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(messages.i18n['form.log.in']);
	}
});

var titleView = new TitleView();

var FormView = Backbone.View.extend({
	el: $(".modal-dialog"),
	template: _.template($("#form-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var formView = new FormView();
