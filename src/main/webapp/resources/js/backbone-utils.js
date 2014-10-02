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

	var GoalModel = Backbone.Model.extend({
		userId : undefined,
		date : undefined,
		weight : undefined
	});

	var CategoryModel = Backbone.Model.extend({
		id: undefined,
		parentId: undefined,
		name: undefined,
		subcategories: []
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

	var DiaryModel = Backbone.Model.extend({
		userId : undefined,
		food : undefined,
		timestamp : undefined,
		weight : undefined
	});

});