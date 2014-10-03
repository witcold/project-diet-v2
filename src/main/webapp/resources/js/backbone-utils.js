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
		userId : undefined,
		date : undefined,
		weight : undefined
	});

	var WeightList = Backbone.Collection.extend({
		model: WeightModel
	});

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
			account: UserModel,
			state: "dashboard"
		}
	});

	var appState = new AppState();

	var Controller = Backbone.Router.extend({
		routes: {
			"": "dashboard"
		},
		dashboard: function() {
			appState.set({
				state: "dashboard"
			});
		}
	});

	var controller = new Controller();

	var View = Backbone.View.extend({
		el: $(".container"),
		templates: {
			"dashboard": _.template($("#template").html())
		},
		initialize: function () {
			this.model.bind("change", this.render, this);
		},
		render: function () {
			var state = this.model.get("state");
			$(this.el).html(this.templates[state](this.model.toJSON()));
			$.get('user', function (result) {
				appState.account = new UserModel(result);
				$(view.el).html(view.templates[view.model.get("state")](result));
			});
			plotWeight();
			plotDiary();
			return this;
		}
	});

	var view = new View({
		model: appState
	});

	appState.trigger("change");

	Backbone.history.start();
