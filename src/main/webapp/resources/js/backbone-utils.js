$(function () {

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

	var Controller = Backbone.Router.extend({
		routes: {
			
		}
	});

	var controller = new Controller();

	Backbone.history.start();

});