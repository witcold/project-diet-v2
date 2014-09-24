function plotEmptyChart(selector, options) {
	var settings = $.extend(true, {}, defaultOptions, options);
	var element = $(selector).highcharts(settings);
	return element.highcharts();
}