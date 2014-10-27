var LanguageView = Backbone.View.extend({
	template: _.template($("#menu-language-template").html()),
	initialize: function () {
	},
	render: function () {
		
	}
});

var NavbarView = Backbone.View.extend({
	template: _.template($("#menu-navbar-template").html()),
	initialize: function () {
	},
	render: function () {
		
	}
});

var LogoutView = Backbone.View.extend({
	template: _.template($("#menu-logout-template").html()),
	initialize: function () {
	},
	render: function () {
		
	}
});

var SignupView = Backbone.View.extend({
	template: _.template($("#menu-signup-template").html()),
	initialize: function () {
	},
	render: function () {
		
	}
});

var LoginView = Backbone.View.extend({
	template: _.template($("#menu-login-template").html()),
	initialize: function () {
	},
	render: function () {
		
	}
});

var MenuView = Backbone.View.extend({
	el: $("#main-menu"),
	initialize: function () {
	},
	render: function () {
		this.$el.empty();
		this.$el.append(languageView.el);
		if (logged) {
			this.$el.append(navbarView.el);
			this.$el.append(logoutView.el);
		} else {
			this.$el.append(signupView.el);
			this.$el.append(loginView.el);
		}
	}
});