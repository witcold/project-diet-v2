var TitleView = Backbone.View.extend({
	el: $("title"),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(messages.i18n['form.sign.up']);
	}
});

var titleView = new TitleView();

var FormView = Backbone.View.extend({
	el: $(".modal-dialog"),
	template: _.template($("#form-template").html()),
	initialize: function () {
		this.render();
	},
	render: function () {
		this.$el.html(this.template(messages));

		$('#datetimepicker').datetimepicker({
			format: 'YYYY.MM.DD',
			pickTime: false,
			useStrict: true
		});

		var input = $('#activityLevel');

		var slider = $('#paeLevel').slider({
			min: 1,
			max: 3,
			precision: 2,
			step: 0.1,
			value: 1.50,
			formatter: function(value) {
				return value;
			}
		}).on('slide', function () {
			input.val(slider.getValue());
		}).data('slider');

		input.on('change', function() {
			slider.setValue(parseFloat(input.val()));
		});
	}
});

var formView = new FormView();
