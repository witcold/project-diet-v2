var TitleView = Backbone.View.extend({
	el: $("title"),
	template: _.template($("#title-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var titleView = new TitleView();

var ContainerView = Backbone.View.extend({
	el: $(".container"),
	template: _.template($("#table-header-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.find('#container-label').html(messages.i18n['label.goal']);
		this.$el.find('#table-header').html(this.template(messages));
		this.$el.find('#add-goal').html(messages.i18n['goal.add']);
	}
});

var containerView = new ContainerView();

var ModalView = Backbone.View.extend({
	el: $(".modal-content"),
	template: _.template($("#modal-template").html()),
	initialize: function () {
		this.render();
		datetimepicker = $('#datetimepicker').datetimepicker({
			format: 'YYYY.MM.DD',
			pickTime: false,
			useStrict: true
		}).data("DateTimePicker");
	},
	render: function () {
		this.$el.html(this.template(messages));
	}
});

var modalView = new ModalView();

var GoalListVeiw = Backbone.View.extend({
	el: $("#goal-table"),
	initialize: function () {
		this.listenTo(this.collection, 'add remove reset', this.render);
	},
	render: function () {
		var self = this;
		this.$el.empty();
		_.each(this.collection.models, function (weight) {
			var view = new GoalListItemView({model: weight});
			self.$el.append(view.el);
			view.render();
		});
	}
});

var GoalListItemView = Backbone.View.extend({
	tagName: 'tr',
	template: _.template($("#goal-tr-template").html()),
	initialize: function () {
		this.listenTo(this.model, 'change', this.render);
	},
	render: function () {
		this.$el.html(this.template(this.model.attributes));
		var self = this;
		this.$el.find('#edit').on('click', function () {
			editForm(self.model.get('date'), self.model.get('weight'));
		});
		this.$el.find('#delete').on('click', function () {
			deleteGoal(self.model.get('date'));
			goals.remove(self.model);
		});
	}
});

var goals = new GoalList();

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "current",
	},
	current: function () {
		goals.fetch();
		var goalListView = new GoalListVeiw({ collection: goals });
	}
});

var datetimepicker;
var goalform = $('#goalForm');

var app = new AppRouter();
Backbone.history.start();

function deleteGoal(date) {
	if (confirm(messages.i18n['form.confirm']))
		$.post('goals/delete', {'date': new Date(date).toLocaleFormat("%Y.%m.%d")});
};

function editForm(date, weight) {
	datetimepicker.setDate(new Date(date));
	goalform.find('#datetimepicker .input-group-addon').hide();
	goalform.find('.date').removeClass('input-group');
	$('#weight').val(weight);
	$('#goalModal').modal('show');
};

$('#goalModal').on('hidden.bs.modal', function (e) {
	goalform.trigger('reset');
	goalform.find('.date').addClass('input-group');
	goalform.find('.input-group-addon').show();
});

function validateDate(event) {
	var date = goalform.find('#datetimepicker input').val();
	return !!date;
}

function sendForm (event) {
	event.preventDefault();
	if (!validateDate(event)) {
		datetimepicker.show(event);
		return;
	}

	var goal = {
		date: goalform.find("input[name='date']").val(),
		weight: goalform.find("input[name='weight']").val()
	};

	var posting = $.post('goals/add', goal);

	posting.done(function () {
		goals.add(goal);
	});
	$('#goalModal').modal('hide');
}