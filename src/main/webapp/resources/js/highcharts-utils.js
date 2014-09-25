var globalOptions = {
	global: {
		useUTC: false
	}
};

var defaultOptions = {
	chart: {
		backgroundColor: null
	},
	legend: {
		enabled: false
	},
	title: {
		text: null
	},
	subtitle: {
		text: null
	},
	xAxis: {
		type: 'datetime',
		tickInterval: 24 * 3600 * 1000, // one day
		gridLineWidth: 1,
		dateTimeLabelFormats: {
			day: '%e.%m',
		}
	},
	yAxis: [
		{
			title: {
				text: null
			}
		},
		{
			linkedTo: 0,
			opposite: true,
			title: {
					text: null
			}
		}
	],
};

function getMillis(date) {
	var dateParts = date.split("-");
	return +new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
}

function process(data, x, y) {
	var result = [];
	for (var i = 0; i < data.length; i++) {
		result.push([getMillis(data[i][x]), data[i][y]]);
	}
	return result;
}

function plotEmptyChart(selector, options) {
	var settings = $.extend(true, {}, defaultOptions, options);
	var element = $(selector).highcharts(settings);
	return element.highcharts();
}