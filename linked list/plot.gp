set terminal pdf               # Set output format to PDF
set output 'output.pdf'        # Name of the output file
set xlabel 'Size of the list'      # Label for the X-axis
set ylabel 'Runtime in ns'      # Label for the Y-axis
set grid                       # Enable grid

# Plot the data using points (dots)
plot 'data.dat' using 1:2 with points pointtype 7 pointsize 1 title 'Data Points'