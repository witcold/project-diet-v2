	var UserModel = Backbone.Model.extend({
		id: undefined,
		login: undefined,
		password: undefined,
		firstName: undefined,
		lastName: undefined,
		gender: undefined,
		birthDate: undefined,
		countryId: undefined,
		height: undefined,
		activityLevel: undefined
	});

	var WeightModel = Backbone.Model.extend({
	});

	var WeightList = Backbone.Collection.extend({
		model: WeightModel,
		url: 'weight/raw'
	});

	var weights = new WeightList();

	var GoalModel = Backbone.Model.extend({
		userId : undefined,
		date : undefined,
		weight : undefined
	});

	var GoalList = Backbone.Collection.extend({
		model: GoalModel
	});

	var CategoryModel = Backbone.Model.extend({
		id: undefined,
		parentId: undefined,
		name: undefined,
		subcategories: []
	});

	var CategoryList = Backbone.Collection.extend({
		model: CategoryModel
	});

	var FoodModel = Backbone.Model.extend({
		id: undefined,
		categoryId: undefined,
		name: undefined,
		calories: undefined,
		proteins: undefined,
		fats: undefined,
		carbohydrates: undefined
	});

	var FoodList = Backbone.Collection.extend({
		model: FoodModel
	});

	var DiaryModel = Backbone.Model.extend({
		userId : undefined,
		food : undefined,
		timestamp : undefined,
		weight : undefined
	});

	var DiaryList = Backbone.Collection.extend({
		model: DiaryModel
	});

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

	var View = Backbone.View.extend({
		el: $(".container"),
		template:  _.template($("#template").html()),
		initialize: function () {
			this.render();
		},
		render: function () {
			weights.fetch();
			var self = this;
			$.get('user', function (result) {
				result.age = Math.floor((Date.now() - getMillis(result.birthDate)) / (1000 * 60 *60 * 24 * 365.25));
				self.$el.html(self.template(result));
				//plotWeight(weightPath, weightValueSuffix, weightChartName, goalWeightChartName);
				//plotDiary(diaryPath, diaryValueSuffix, diaryChartName, goalDiaryChartName);
			});
			return this;
		}
	});

	var view = new View();

	Backbone.history.start();
