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