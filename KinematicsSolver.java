public class KinematicsSolver

{

	public Double v1x, v1y, v2x, v2y, ax, ay, dx, dy, tx, ty;
	public double ymax = Double.NaN;


	//Constructor: creates a solver with the given known values, pass null for unknowns.

	public KinematicsSolver(Double v1x, Double v1y,
	                        Double v2x, Double v2y,
	                        Double ax,  Double ay,
	                        Double dx,  Double dy,
	                        Double tx,  Double ty)

	{

		this.v1x = v1x; this.v1y = v1y;
		this.v2x = v2x; this.v2y = v2y;
		this.ax  = ax;  this.ay  = ay;
		this.dx  = dx;  this.dy  = dy;
		this.tx  = tx;  this.ty  = ty;

	}


	//Solves for all unknown kinematic variables and calculates ymax.

	public void solve()

	{

		if (tx != null && tx <= 0) tx = null;
		if (ty != null && ty <= 0) ty = null;

		if (tx != null && ty == null) ty = tx;
		else if (ty != null && tx == null) tx = ty;

		if (ax == null) ax = 0.0;
		if (ay == null) ay = -9.81;

		solveXAxis();
		solveYAxis();

		if (tx == null && ty != null) tx = ty;
		if (ty == null && tx != null) ty = tx;

		if (dx == null && v1x != null && tx != null)
			dx = v1x * tx + 0.5 * ax * tx * tx;
		else if (v1x == null && dx != null && tx != null && tx != 0)
			v1x = (dx - 0.5 * ax * tx * tx) / tx;

		if (ax == 0.0)
		{
			if (v2x == null && v1x != null) v2x = v1x;
			if (v1x == null && v2x != null) v1x = v2x;
		}

		if (v1y != null && ay != null && ay != 0)
		{
			double tPeak = -v1y / ay;
			if (tPeak >= 0)
				ymax = v1y * tPeak + 0.5 * ay * tPeak * tPeak;
			else
				ymax = 0;
		}

	}


	//Solves the horizontal axis using v2x = v1x + ax*tx and dx = v1x*tx + 0.5*ax*tx^2.

	private void solveXAxis()

	{

		if (v1x == null && v2x != null && tx != null) v1x = v2x - ax * tx;
		if (v2x == null && v1x != null && tx != null) v2x = v1x + ax * tx;

		if (ax == 0.0)
		{
			if (v1x == null && v2x != null) v1x = v2x;
			if (v2x == null && v1x != null) v2x = v1x;
		}

		if (dx == null && v1x != null && tx != null)
			dx = v1x * tx + 0.5 * ax * tx * tx;
		else if (v1x == null && dx != null && tx != null && tx != 0)
			v1x = (dx - 0.5 * ax * tx * tx) / tx;
		else if (tx == null && ax == 0.0 && v1x != null && dx != null && v1x != 0)
			tx = dx / v1x;

		if (v2x == null && v1x != null && tx != null) v2x = v1x + ax * tx;
		if (ax == 0.0 && v2x == null && v1x != null)  v2x = v1x;

	}


	//Solves the vertical axis; handles symmetry, quadratic time, and known-time cases.

	private void solveYAxis()

	{

		if (dy != null && dy == 0.0)
		{
			if (v1y != null && v2y == null) v2y = -v1y;
			else if (v2y != null && v1y == null) v1y = -v2y;
		}

		int known = countKnown(v1y, v2y, dy, ty);
		if (known < 2 || ay == null) return;

		if (ty == null)
		{

			if (v1y != null && v2y != null)
			{
				ty = (v2y - v1y) / ay;
				if (ty <= 0) { ty = null; return; }
				if (dy == null) dy = 0.5 * (v1y + v2y) * ty;
			}
			else if (v1y != null && dy != null)
			{
				double a = 0.5 * ay, b = v1y, c = -dy;
				double disc = b * b - 4 * a * c;
				if (disc >= 0)
				{
					double t1 = (-b + Math.sqrt(disc)) / (2 * a);
					double t2 = (-b - Math.sqrt(disc)) / (2 * a);
					if      (t1 > 0 && (t2 <= 0 || t1 < t2)) ty = t1;
					else if (t2 > 0)                           ty = t2;
					if (ty != null) v2y = v1y + ay * ty;
				}
			}
			else if (v2y != null && dy != null)
			{
				double a = 0.5 * ay, b = -v2y, c = dy;
				double disc = b * b - 4 * a * c;
				if (disc >= 0)
				{
					double t1 = (-b + Math.sqrt(disc)) / (2 * a);
					double t2 = (-b - Math.sqrt(disc)) / (2 * a);
					if      (t1 > 0 && (t2 <= 0 || t1 < t2)) ty = t1;
					else if (t2 > 0)                           ty = t2;
					if (ty != null) v1y = v2y - ay * ty;
				}
			}

		}
		else
		{

			if (v2y == null && dy == null && v1y != null)
			{
				dy  = v1y * ty + 0.5 * ay * ty * ty;
				v2y = v1y + ay * ty;
			}
			else if (v1y == null && dy == null && v2y != null)
			{
				dy  = v2y * ty - 0.5 * ay * ty * ty;
				v1y = v2y - ay * ty;
			}
			else if (v2y == null && v1y != null)
			{
				v2y = v1y + ay * ty;
				if (dy == null) dy = 0.5 * (v1y + v2y) * ty;
			}
			else if (v1y == null && v2y != null)
			{
				v1y = v2y - ay * ty;
				if (dy == null) dy = 0.5 * (v1y + v2y) * ty;
			}
			else if (dy == null && v1y != null && v2y != null)
			{
				dy = 0.5 * (v1y + v2y) * ty;
			}

		}

	}


	//Returns the number of non-null values in the supplied list.

	private int countKnown(Double... vals)

	{

		int n = 0;
		for (Double v : vals) if (v != null) n++;
		return(n);

	}

}
