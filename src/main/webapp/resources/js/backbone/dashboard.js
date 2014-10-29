var TitleView = Backbone.View.extend({
	el: $("title"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(messages.i18n['label.dashboard']);
	}
});

var UserView = Backbone.View.extend({
	el: $(".personal"),
	template: _.template($("#personal-template").html()),
	initialize: function () {
		this.model.set("id", "user@domain");
		this.listenTo(this.model, 'change', this.render);
		this.model.fetch();
	},
	render: function () {
		this.$el.html(this.template($.extend({}, messages, this.model.attributes)));
	}
});

var WeightView = Backbone.View.extend({
	el: $(".weight"),
	template: _.template($("#weight-template").html()),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
		var now = new Date();
		now.setHours(0,0,0,0);
		now.setDate(1);
		var nextFrom = new Date(now);
		nextFrom.setMonth(now.getMonth() + 1);
		this.collection.fromDate = now;
		this.collection.toDate = nextFrom;
		this.collection.fetch();
	},
	render: function () {
		var lastWeight = this.collection.at(this.collection.length - 1);
		lastWeight.set("bmi", lastWeight.get("weight") / Math.pow(user.get("height") / 100, 2));
		this.$el.html(this.template(lastWeight.attributes));
	}
});

var CaloriestView = Backbone.View.extend({
	el: $(".calories"),
	template: _.template($("#calories-template").html()),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
		this.collection.fetch();
	},
	render: function () {
		var lastBmr = this.collection.at(this.collection.length - 1);
		lastBmr.set("activityLevel", user.get("activityLevel"));
		this.$el.html(this.template(lastBmr.attributes));
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "dashboard"
	},
	dashboard: function () {
		var titleView = new TitleView();
		var userView = new UserView({
			model: user
		});
		var weightView = new WeightView({
			collection: weights
		});
		var caloriesView = new CaloriestView({
			collection: bmrs
		});
		plotWeight(weightPath, weightValueSuffix, weightChartName, goalWeightChartName);
		plotDiary(diaryPath, diaryValueSuffix, diaryChartName, goalDiaryChartName);
	}
});

var app = new AppRouter();

Backbone.history.start();
