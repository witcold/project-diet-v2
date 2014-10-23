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

var app = new AppRouter();

Backbone.history.start();

$('#datetimepicker').datetimepicker({
	format: 'YYYY.MM.DD',
	pickTime: false,
	useStrict: true
});
var datetimepicker = $('#datetimepicker').data("DateTimePicker");
var goalform = $('#goalForm');

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