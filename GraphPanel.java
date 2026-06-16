import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel

{

	private double v1x     = Double.NaN;
	private double v1y     = Double.NaN;
	private double ay      = -9.81;
	private double totalDt = Double.NaN;
	private double launchY = 0;

	private static final int   MARGIN     = 55;
	private static final Color COLOR_PATH = new Color(30, 100, 200);
	private static final Color COLOR_PEAK = new Color(220, 50, 50);
	private static final Color COLOR_LAND = new Color(30, 160, 30);
	private static final Color COLOR_AXIS = new Color(60, 60, 60);
	private static final Color COLOR_GRID = new Color(210, 210, 215);


	//Stores the solved values needed to draw the trajectory; call repaint() after.

	public void setSolvedValues(double v1x, double v1y, double ay,
	                            double dx,  double dy,  double dt,
	                            double launchY)

	{

		this.v1x     = v1x;
		this.v1y     = v1y;
		this.ay      = ay;
		this.totalDt = dt;
		this.launchY = launchY;

	}


	//Draws the full trajectory graph including background, grid, axes, path, and labels.

	@Override
	protected void paintComponent(Graphics g0)

	{

		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int W = getWidth();
		int H = getHeight();

		g.setColor(new Color(248, 248, 252));
		g.fillRect(0, 0, W, H);

		if (Double.isNaN(v1x) || Double.isNaN(v1y) || Double.isNaN(totalDt) || totalDt <= 0)
		{
			g.setColor(Color.GRAY);
			g.setFont(new Font("SansSerif", Font.ITALIC, 14));
			g.drawString("Enter enough variables to see the trajectory.", MARGIN, H / 2);
			drawAxes(g, W, H, MARGIN, H - MARGIN, MARGIN, W - MARGIN);
			return;
		}

		int steps = 500;
		double[] xs = new double[steps + 1];
		double[] ys = new double[steps + 1];
		double xMin = 0, xMax = 0, yMin = 0, yMax = 0;

		for (int i = 0; i <= steps; i++)
		{
			double t = totalDt * i / steps;
			xs[i] = v1x * t;
			ys[i] = launchY + v1y * t + 0.5 * ay * t * t;
			if (xs[i] < xMin) xMin = xs[i];
			if (xs[i] > xMax) xMax = xs[i];
			if (ys[i] < yMin) yMin = ys[i];
			if (ys[i] > yMax) yMax = ys[i];
		}

		xMin = Math.min(xMin, 0);  xMax = Math.max(xMax, 0);
		yMin = Math.min(yMin, 0);  yMax = Math.max(yMax, 0);

		double xPad = Math.max((xMax - xMin) * 0.12, 1);
		double yPad = Math.max((yMax - yMin) * 0.12, 1);
		xMin -= xPad;  xMax += xPad;
		yMin -= yPad;  yMax += yPad;

		double scaleX = (W - 2.0 * MARGIN) / (xMax - xMin);
		double scaleY = (H - 2.0 * MARGIN) / (yMax - yMin);
		double offX   = MARGIN - xMin * scaleX;
		double offY   = (H - MARGIN) + yMin * scaleY;

		int axisScreenY = sy(0, offY, scaleY);
		int axisScreenX = sx(0, offX, scaleX);

		drawGrid(g, W, H, xMin, xMax, yMin, yMax, offX, offY, scaleX, scaleY);
		drawAxes(g, W, H, axisScreenY, H - MARGIN, axisScreenX, W - MARGIN);
		drawPath(g, xs, ys, steps, offX, offY, scaleX, scaleY);
		drawKeyPoints(g, xs, ys, steps, offX, offY, scaleX, scaleY);

	}


	//Draws the X and Y axes through the world origin with arrow tips and axis labels.

	private void drawAxes(Graphics2D g, int W, int H,
	                      int yAxisRow, int xAxisBottom,
	                      int xAxisCol, int yAxisRight)

	{

		g.setColor(COLOR_AXIS);
		g.setStroke(new BasicStroke(1.8f));
		g.setFont(new Font("SansSerif", Font.BOLD, 11));

		g.drawLine(MARGIN - 8, yAxisRow, yAxisRight + 8, yAxisRow);
		g.fillPolygon(new int[]{yAxisRight + 8, yAxisRight, yAxisRight},
		              new int[]{yAxisRow, yAxisRow - 4, yAxisRow + 4}, 3);
		g.drawString("X (m)", yAxisRight - 30, yAxisRow - 6);

		g.drawLine(xAxisCol, xAxisBottom + 8, xAxisCol, MARGIN - 8);
		g.fillPolygon(new int[]{xAxisCol,  xAxisCol - 4, xAxisCol + 4},
		              new int[]{MARGIN - 8, MARGIN,       MARGIN}, 3);
		g.drawString("Y (m)", xAxisCol + 6, MARGIN + 2);

	}


	//Draws grid lines and numeric tick labels at evenly spaced world-coordinate intervals.

	private void drawGrid(Graphics2D g, int W, int H,
	                      double xMin, double xMax, double yMin, double yMax,
	                      double offX, double offY, double scaleX, double scaleY)

	{

		Font tickFont = new Font("SansSerif", Font.PLAIN, 9);

		double yStep = niceStep((yMax - yMin) / 6);
		for (double y = Math.ceil(yMin / yStep) * yStep; y <= yMax; y += yStep)
		{
			int row = sy(y, offY, scaleY);
			if (row < MARGIN || row > H - MARGIN) continue;
			g.setColor(COLOR_GRID);
			g.setStroke(new BasicStroke(0.5f));
			g.drawLine(MARGIN, row, W - MARGIN, row);
			g.setColor(Color.GRAY);
			g.setFont(tickFont);
			g.drawString(String.format("%.1f", y), 2, row + 4);
		}

		double xStep = niceStep((xMax - xMin) / 6);
		for (double x = Math.ceil(xMin / xStep) * xStep; x <= xMax; x += xStep)
		{
			int col = sx(x, offX, scaleX);
			if (col < MARGIN || col > W - MARGIN) continue;
			g.setColor(COLOR_GRID);
			g.setStroke(new BasicStroke(0.5f));
			g.drawLine(col, MARGIN, col, H - MARGIN);
			g.setColor(Color.GRAY);
			g.setFont(tickFont);
			g.drawString(String.format("%.1f", x), col - 10, H - MARGIN + 12);
		}

	}


	//Draws the projectile trajectory as a series of connected line segments.

	private void drawPath(Graphics2D g, double[] xs, double[] ys, int steps,
	                      double offX, double offY, double scaleX, double scaleY)

	{

		g.setColor(COLOR_PATH);
		g.setStroke(new BasicStroke(2.5f));
		for (int i = 0; i < steps; i++)
		{
			g.drawLine(sx(xs[i],     offX, scaleX), sy(ys[i],     offY, scaleY),
			           sx(xs[i + 1], offX, scaleX), sy(ys[i + 1], offY, scaleY));
		}

	}


	//Draws coloured dots and coordinate labels at the launch, peak, and landing points.

	private void drawKeyPoints(Graphics2D g, double[] xs, double[] ys, int steps,
	                           double offX, double offY, double scaleX, double scaleY)

	{

		g.setFont(new Font("SansSerif", Font.PLAIN, 11));

		int lax = sx(xs[0], offX, scaleX);
		int lay = sy(ys[0], offY, scaleY);
		g.setColor(new Color(100, 0, 180));
		g.fillOval(lax - 5, lay - 5, 10, 10);
		g.setColor(Color.DARK_GRAY);
		g.drawString(String.format("Launch (%.1f, %.1f)", xs[0], ys[0]), lax + 8, lay - 4);

		double tPeak = (ay != 0) ? -v1y / ay : 0;
		if (tPeak > 0 && tPeak < totalDt)
		{
			double xPeak = v1x * tPeak;
			double yPeak = launchY + v1y * tPeak + 0.5 * ay * tPeak * tPeak;
			int px = sx(xPeak, offX, scaleX);
			int py = sy(yPeak, offY, scaleY);
			g.setColor(COLOR_PEAK);
			g.fillOval(px - 6, py - 6, 12, 12);
			g.setColor(Color.DARK_GRAY);
			g.drawString(String.format("Peak (%.1f, %.1f)", xPeak, yPeak), px + 8, py - 4);
		}

		int lx = sx(xs[steps], offX, scaleX);
		int ly = sy(ys[steps], offY, scaleY);
		g.setColor(COLOR_LAND);
		g.fillOval(lx - 6, ly - 6, 12, 12);
		g.setColor(Color.DARK_GRAY);
		g.drawString(String.format("Land (%.1f, %.1f)", xs[steps], ys[steps]), lx + 8, ly + 14);

	}


	//Returns a round grid step size appropriate for the given rough interval.

	private double niceStep(double rough)

	{

		if (rough <= 0) return(1);
		double mag  = Math.pow(10, Math.floor(Math.log10(rough)));
		double frac = rough / mag;
		if (frac < 1.5) return(mag);
		if (frac < 3.5) return(2 * mag);
		if (frac < 7.5) return(5 * mag);
		return(10 * mag);

	}


	//Converts a world X coordinate to a screen X pixel.

	private int sx(double x, double offX, double scaleX)

	{

		return((int) Math.round(offX + x * scaleX));

	}


	//Converts a world Y coordinate to a screen Y pixel with Y axis inverted.

	private int sy(double y, double offY, double scaleY)

	{

		return((int) Math.round(offY - y * scaleY));

	}

}
