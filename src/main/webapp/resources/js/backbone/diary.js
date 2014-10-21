_.templateSettings = {
	evaluate: /\(@([\s\S]+?)@\)/g,
	interpolate: /\(@=(.+?)@\)/g,
	escape: /\(@-([\s\S]+?)@\)/g
};

var DateView = Backbone.View.extend({
	el: $('#date'),
	template: _.template($("#diary-day-pager-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(this.model));
	}
});

var DiaryListVeiw = Backbone.View.extend({
	el: $("#diary-table"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (diary) {
			var view = new DiaryListItemView({model: diary});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var DiaryListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#diary-tr-template").html()),
	initialize: function () {
		this.listenTo(this.model, 'change', this.render);
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
	}
});

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "current",
		":date": "date"
	},
	current: function () {
		var now = new Date();
		now.setHours(0,0,0,0);
		var prev = new Date(now);
		prev.setDate(now.getDate() - 1);
		var next = new Date(now);
		next.setDate(now.getDate() + 1);
		var dateView = new DateView({
			model: {
				now: now,
				prev: prev.toLocaleFormat("%Y-%m-%d"),
				next: next.toLocaleFormat("%Y-%m-%d"),
			}
		});
		var diaries = new DiaryList();
		diaries.date = now;
		diaries.fetch();
		var diaryListVeiw = new DiaryListVeiw({ collection: diaries });
	},
	date: function (date) {
		var now = new Date(Date.parse(date));
		now.setHours(0,0,0,0);
		var prev = new Date(now);
		prev.setDate(now.getDate() - 1);
		var next = new Date(now);
		next.setDate(now.getDate() + 1);
		var dateView = new DateView({
			model: {
				now: now,
				prev: prev.toLocaleFormat("%Y-%m-%d"),
				next: next.toLocaleFormat("%Y-%m-%d"),
			}
		});
		var diaries = new DiaryList();
		diaries.date = now;
		diaries.fetch({ reset: true });
		var diaryListVeiw = new DiaryListVeiw({ collection: diaries });
	}
});

var app = new AppRouter();

Backbone.history.start();
