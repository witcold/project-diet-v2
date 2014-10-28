var LanguageView = Backbone.View.extend({
	tagName: 'div',
	className: 'dropdown navbar-form navbar-right',
	template: _.template($("#menu-language-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var NavbarView = Backbone.View.extend({
	tagName: 'ul',
	className: 'nav navbar-nav',
	template: _.template($("#menu-navbar-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var LogoutView = Backbone.View.extend({
	tagName: 'div',
	className: 'navbar-form navbar-right navbar-text',
	template: _.template($("#menu-logout-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var SignupView = Backbone.View.extend({
	tagName: 'div',
	className: 'navbar-form navbar-right',
	template: _.template($("#menu-signup-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var LoginView = Backbone.View.extend({
	tagName: 'form',
	className: 'navbar-form navbar-right',
	attributes: {
		action: 'login',
		method: 'POST',
		'accept-charset': 'UTF-8'
	},
	template: _.template($("#menu-login-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var MenuView = Backbone.View.extend({
	el: $("#main-menu"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.empty();
		var languageView = new LanguageView();
		this.$el.append(languageView.el);
		var self = this;
		$.get('users', function (data) {
			if (data.login) {
				var navbarView = new NavbarView();
				self.$el.append(navbarView.el);
				var logoutView = new LogoutView();
				self.$el.append(logoutView.el);
			} else {
				var signupView = new SignupView();
				self.$el.append(signupView.el);
				var loginView = new LoginView();
				self.$el.append(loginView.el);
			}
		});
	}
});

var menu = new MenuView();
