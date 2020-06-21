mkdir final_lint_reports
for dir in */; do
	cd $dir
	if [ -d "build/reports/" ]; then
		echo "report directory found"
		cd build/reports/

		cp detekt/detekt.html ../../../final_lint_reports/
		cp ktlint/ktlintMainSourceSetCheck.xml ../../../final_lint_reports/
		cp lint-results.html ../../../final_lint_reports/
		cd ../../
	fi
	cd ..
done