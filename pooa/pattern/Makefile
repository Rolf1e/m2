install:
	mkdir build
	pdflatex --output-directory build presentation.tex

compile:
	pdflatex --output-directory build presentation.tex

open:
	~/software/firefox/firefox -new-tab=file:///media/rolfie/ssd2/projects/cours/m2/pooa/pattern/build/presentation.pdf

pdf: compile open
