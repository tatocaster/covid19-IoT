mkdir final_lint_reports
for dir in */; do
	cd $dir
	if [ -d "build/reports/" ]; then
		echo "report directory found"
		cd build/reports/

		cp detekt/detekt.html ../../../final_lint_reports/detekt-${dir%?}.html
		cp ktlint/ktlintMainSourceSetCheck.xml ../../../final_lint_reports/ktlin-${dir%?}.xml
		cp lint-results.html ../../../final_lint_reports/linter-${dir%?}.html
		cd ../../
	fi
	cd ..
done