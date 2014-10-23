if (!Date.prototype.toLocaleFormat) {
	Date.prototype.toLocaleFormat = function (format) {
		var f = {
			Y: this.getFullYear(),
			y: this.getFullYear() - (this.getFullYear() >= 2000? 2000 : 1900),
			m: this.getMonth() + 1,
			d: this.getDate(),
			H: this.getHours(),
			M: this.getMinutes(),
			S: this.getSeconds()
		}, k;
		for(k in f)
			format = format.replace('%' + k, f[k] < 10 ? '0' + f[k] : f[k]);
		return format;
	}
};

var UserModel = Backbone.Model.extend({
	urlRoot: 'users',
	parse: function (response, options) {
		response.age = Math.floor((Date.now() - getMillis(response.birthDate)) / (1000 * 60 *60 * 24 * 365.25));
		return response;
	}
});

var user = new UserModel();

var WeightModel = Backbone.Model.extend({
});

var WeightList = Backbone.Collection.extend({
	model: WeightModel,
	url: function() {
		return 'weights/' + this.fromDate.toLocaleFormat('%Y-%m-%d') + '/' + this.toDate.toLocaleFormat('%Y-%m-%d');
	}
});

var weights = new WeightList();

var GoalModel = Backbone.Model.extend({
});

var GoalList = Backbone.Collection.extend({
	model: GoalModel,
	url: 'goals'
});

var CategoryModel = Backbone.Model.extend({
});

var CategoryList = Backbone.Collection.extend({
	model: CategoryModel,
	url: 'categories'
});

var categories = new CategoryList();

var FoodModel = Backbone.Model.extend({
});

var FoodList = Backbone.Collection.extend({
	model: FoodModel,
	url: function() {
		if (this.category) {
			return 'foods/category/' + this.category;
		}
		return 'foods';
	}
});

var DiaryModel = Backbone.Model.extend({
});

var DiaryList = Backbone.Collection.extend({
	model: DiaryModel,
	url: function() {
		return 'diaries/' + this.date.toLocaleFormat("%Y-%m-%d");
	}
});

var BMRModel = Backbone.Model.extend({
});

var BMRList = Backbone.Collection.extend({
	model: BMRModel,
	url: 'weight/bmr'
});

var bmrs = new BMRList();
