public class NBody {
    public static void main(String[] args) {

        // Step 1. Parse command-line arguments.

        double tau = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        // Step 2. Read universe from standard input.

        int n = StdIn.readInt();
        double radius = StdIn.readDouble();

        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {

            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();

        }
        // Step 3. Initialize standard drawing.

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        // Step 4. Play music on standard audio.

        // StdAudio.play("2001.wav");

        // Step 5. Simulate the universe.

        for (double t = 0.0; t < tau; t += dt) {

            // Step 5A. Calculate net forces.

            double[] fx = new double[n];
            double[] fy = new double[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {

                        double dx = px[j] - px[i];
                        double dy = py[j] - py[i];

                        double r = dx * dx + dy * dy;

                        double G = 6.67e-11 * n;

                        double force = G * mass[i] * mass[j] / r;

                        fx[i] += force * dx / radius;
                        fy[i] += force * dy / radius;

                    }
                }
            }

            // Step 5B. Update velocities and positions.

            StdDraw.picture(0, 0, "starfield.jpg");

            // double ax = 0;
            // double ay = 0;

            for (int i = 0; i < n; i++) {

                double ax = fx[i] / mass[i];
                double ay = fy[i] / mass[i];

                vx[i] += ax * dt;
                vy[i] += ay * dt;


                px[i] += vx[i] * dt;
                py[i] += vy[i] * dt;

                StdDraw.picture(vx[i] + px[i], py[i] + vy[i], image[i]);
            }

            // Step 5C. Draw universe to standard drawing.

            StdDraw.show();
            StdDraw.pause(20);

            // Step 6. Print universe to standard output.

            StdOut.printf("%d\n", n);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < n; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                              px[i], py[i], vx[i], vy[i], mass[i], image[i]);
            }
        }
    }
}


