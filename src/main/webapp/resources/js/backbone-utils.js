	var UserModel = Backbone.Model.extend({
		urlRoot: 'users'
	});

	var user = new UserModel();

	var WeightModel = Backbone.Model.extend({
	});

	var WeightList = Backbone.Collection.extend({
		model: WeightModel,
		url: 'weight/raw'
	});

	var weights = new WeightList();

	var GoalModel = Backbone.Model.extend({
	});

	var GoalList = Backbone.Collection.extend({
		model: GoalModel
	});

	var CategoryModel = Backbone.Model.extend({
	});

	var CategoryList = Backbone.Collection.extend({
		model: CategoryModel
	});

	var FoodModel = Backbone.Model.extend({
	});

	var FoodList = Backbone.Collection.extend({
		model: FoodModel
	});

	var DiaryModel = Backbone.Model.extend({
	});

	var DiaryList = Backbone.Collection.extend({
		model: DiaryModel
	});

	var BMRModel = Backbone.Model.extend({
	});

	var BMRList = Backbone.Collection.extend({
		model: BMRModel,
		url: 'weight/bmr'
	});

	var bmrs = new BMRList();

	var AppState = Backbone.Model.extend({
		defaults: {
			account: UserModel
		}
	});

	var appState = new AppState();

	var Controller = Backbone.Router.extend({
		routes: {
			"": "dashboard"
		},
		dashboard: function() {
		}
	});

	var controller = new Controller();

	_.templateSettings = {
		interpolate: /\{\{(.+?)\}\}/g
	};

	var UserView = Backbone.View.extend({
		el: $(".personal"),
		template:  _.template($("#personal-template").html()),
		render: function () {
			var self = this;
			user.set("id", "user@domain");
			user.fetch({
				success: function (model) {
					console.log(model);
					model.set("age", Math.floor((Date.now() - getMillis(model.get("birthDate"))) / (1000 * 60 *60 * 24 * 365.25)));
					self.$el.html(self.template(model.attributes));
					
				}
			});
			return this;
		}
	});

	var userView = new UserView();

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
