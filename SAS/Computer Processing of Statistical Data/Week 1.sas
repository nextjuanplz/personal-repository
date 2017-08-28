/*
Week 1
Juan Manuel Alzate Vanegas
STA 4102
8/21-25/2017
*/

/*Print text:
Notice that SAS is not case-sensitive!*/
PuT 'Hello World!';


/*Data step*/
	/*Inputting data from a file*/
	procedure dataStepSkeleton
		*	data creates a dynamic dataset
			which can be made persistent
			by attaching it to a library
			name variable
			as DATA <data-set-name-1 <(data-set-options-1)>>
		;
		data x;
		MyDataLib.temp1;


		*	Input data from input.data	csv
			and store in variables a, b, and c
		;
		infile "input.data"; input a b c;

	*Run the procedure;
	run;
	*End the procedure;
	end procedure

	/*Inputting data manually*/
	procedure dataStepSkeleton2
		data one;
		*Input numerical and string data;
		input name $sex$ age income;

		*	Specify datalines for manual input
			in format "a b c" using
			"datalines" or "cards"
		;
		cards;
			Alex M 18 1000
			Bob M 19 2000
			Catherine F 20 3000
			Dorothy F 21 4000
			Eliza F 22 5000
			Felix M 23 6000
			;
	run;
	end procedure

	/*Use keyword "include" to include commands 
	from a file*/
	procedure readExternalCommands
		proc print data = one;
	run;
	end procedure;

	procedure dataStepSkeleton3
		data temp;
		input c;
		datalines;
			1
			2
			3
			4
			5
			;
	run;
	end procedure;

	/*Summarize data:
		N: sample size
		Mean
		Std. Dev
		Min
		Max
	*/
	procedure theMeans
		proc means data = temp;
	run;
	end procedure;


	/*Plotting data*/
	procedure plotData
		data inputData;
		input i month $petNum;
		datalines;
			Jan 129
			Feb 151
			Mar 126
			Apr 128
			May 143
			Jun 200
			Jul 285
			Aug 288
			Sep 247
			Oct 238
			Nov 253
			Dec 279
			;
		run;

		*Plot the data by month;
		proc plot data = inputData;
			plot i*petNum $month; by month;
		run;


		*Create scatterplot;
		proc sgplot data = inputData;
			*Label axes;
			xaxis label = "month of 2016";
			yaxis label = "pet adoption number";

			*Select variables for scatterplot;
			scatter x = month y = petNum;

			*Add regression line with CLI and CLM;
			reg x = ind y = petNum / CLI CLM;

			*Add Locally-weighted scatterplot smoothing (LOESS/LOWESS)
			model;
			loess x = ind y = petNum;

		run;
	run;
	end procedure;

/*Longley economic data (US Dept. of Labor, March 1963)*/
	data econ;
	infile datalines delimiter=',';
	input year$ GNPdeflator GNP Unemployed ArmedForces
	Population Year Employed;
	datalines;
		"1947",83,234.289,235.6,159,107.608,1947,60.323
		"1948",88.5,259.426,232.5,145.6,108.632,1948,61.122
		"1949",88.2,258.054,368.2,161.6,109.773,1949,60.171
		"1950",89.5,284.599,335.1,165,110.929,1950,61.187
		"1951",96.2,328.975,209.9,309.9,112.075,1951,63.221
		"1952",98.1,346.999,193.2,359.4,113.27,1952,63.639
		"1953",99,365.385,187,354.7,115.094,1953,64.989
		"1954",100,363.112,357.8,335,116.219,1954,63.761
		"1955",101.2,397.469,290.4,304.8,117.388,1955,66.019
		"1956",104.6,419.18,282.2,285.7,118.734,1956,67.857
		"1957",108.4,442.769,293.6,279.8,120.445,1957,68.169
		"1958",110.8,444.546,468.1,263.7,121.95,1958,66.513
		"1959",112.6,482.704,381.3,255.2,123.366,1959,68.655
		"1960",114.2,502.601,393.1,251.4,125.368,1960,69.564
		"1961",115.7,518.173,480.6,257.2,127.852,1961,69.331
		"1962",116.9,554.894,400.7,282.7,130.081,1962,70.551
		;
	run;

	/*Summarize dataset*/
	*Data summary;
	proc means data = econ;
	run;

	*Plot;
	proc plot data = econ;
		plot Year*Employed $Year;
	run;

	*Scatterplot;
	proc sgplot data = econ;
		xaxis label = "Year";

		*Not accounting for inflation
			markerattrs: marker attributes
			size: adjusts radius of circles
		;
		scatter x = Year y = GNP / markerattrs = (size = 10);

		*Accounting for inflation
			symbol: adjusts the marker for each data point
		;
		scatter x = Year y = GNPdeflator / markerattrs = (symbol = CircleFilled size = 10);

	run;  

*Run code;
run;
