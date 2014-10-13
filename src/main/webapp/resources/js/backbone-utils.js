_.templateSettings = {
	interpolate: /\{\{(.+?)\}\}/g
};

var UserView = Backbone.View.extend({
	el: $(".personal"),
	template:  _.template($("#personal-template").html()),
	initialize: function () {
		this.listenTo(this.model,'change', this.render);
		this.model.set("id", "user@domain");
		this.model.fetch();
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
	}
});

var userView = new UserView({
	model: user
});

var WeightView = Backbone.View.extend({
	el: $(".weight"),
	template:  _.template($("#weight-template").html()),
	render: function () {
		var self = this;
		weights.fetch({
			success: function (collection) {
				console.log(collection);
				var lastWeight = collection.at(collection.length - 1);
				lastWeight.set("bmi", lastWeight.get("weight") / Math.pow(user.get("height")/100, 2) );
				self.$el.html(self.template(lastWeight.attributes));
			}
		});
		return this;
	}
});

var weightView = new WeightView();

var CaloriestView = Backbone.View.extend({
	el: $(".calories"),
	template:  _.template($("#calories-template").html()),
	render: function () {
		var self = this;
		bmrs.fetch({
			success: function (collection) {
				console.log(collection);
				var lastBmr = collection.at(collection.length - 1);
				lastBmr.set("activityLevel", user.get("activityLevel"));
				self.$el.html(self.template(lastBmr.attributes));
			}
		});
		return this;
	}
});

var caloriesView = new CaloriestView();

var AppState = Backbone.Model.extend({
	defaults: {
		account: UserModel
	}
});

var appState = new AppState();

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "dashboard"
	},
	dashboard: function () {
		userView.render();
		weightView.render();
		caloriesView.render();
		plotWeight(weightPath, weightValueSuffix, weightChartName, goalWeightChartName);
		plotDiary(diaryPath, diaryValueSuffix, diaryChartName, goalDiaryChartName);
	}
});

var app = new AppRouter();

Backbone.history.start();
