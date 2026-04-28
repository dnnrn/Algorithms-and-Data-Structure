set terminal pdf                  # Set output format to PDF
set output 'output.pdf'            # Name of the output file
set xlabel 'Size of the list'      # Label for the X-axis
set ylabel 'Runtime in ns'         # Label for the Y-axis
set grid                           # Enable grid


# Define the colors and styles for the two datasets with smaller dots
set style line 1 lc rgb '#FF69B4' pt 7 ps 0.4   # Pink points for Runtime1, smaller dots
set style line 2 lc rgb '#FFA500' pt 5 ps 0.4   # Orange points for Runtime2, smaller dots



# Plot both datasets from the same file 'que.dat'
plot 'que.dat' using 1:2 with points linestyle 1 title 'Enqueue', \
     'que.dat' using 1:3 with points linestyle 2 title 'dequeue'