//Programmer: Mathew Sinoj
//Description: Detect Colonies for size color and location
//Date Created: May 22, 2026
//Date Revised: June 15, 2026


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;



public class ProjectileCalculator extends JFrame

{
    //sets the variables need for the program
	private JTextField txt_v1x, txt_v1y, txt_v2x, txt_v2y;
	private JTextField txt_ax, txt_ay, txt_dx, txt_dy, txt_tx, txt_ty;
	private JLabel lbl_maxHeight, lbl_totalRange;
	private JButton btn_clear, btn_graph;
	private JRadioButton rb_type1, rb_type2, rb_type3;
	private JPanel pnl_h0, controlPanel;
	private JTextField txt_h0;
	private GraphPanel graphPanel;
	private KinematicsSolver lastSolver = null;
	private boolean updatingFields = false;


	//Sets up and displays the calculator window.

	public ProjectileCalculator()

	{

		setTitle("Projectile Motion Calculator");
		setSize(1100, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		buildTypePanel();
		buildControlPanel();
		buildGraphPanel();

		setLocationRelativeTo(null);
		setVisible(true);

		applyType1Rules();

	}


	//Builds the radio button panel for choosing the projectile type.

	private void buildTypePanel()

	{

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		typePanel.setBorder(BorderFactory.createTitledBorder("Projectile Type"));

		rb_type1 = new JRadioButton("Type 1 â Horizontal Launch");
		rb_type2 = new JRadioButton("Type 2 â Ground to Ground");
		rb_type3 = new JRadioButton("Type 3 â Asymmetric / Cliff");
		rb_type1.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(rb_type1);
		group.add(rb_type2);
		group.add(rb_type3);

		typePanel.add(rb_type1);
		typePanel.add(rb_type2);
		typePanel.add(rb_type3);

		rb_type1.addActionListener(e -> applyType1Rules());
		rb_type2.addActionListener(e -> applyType2Rules());
		rb_type3.addActionListener(e -> applyType3Rules());

		add(typePanel, BorderLayout.NORTH);

	}


	//Builds the left panel containing the input grid, summary labels, and buttons.

	private void buildControlPanel()

	{

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setPreferredSize(new Dimension(380, 0));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		controlPanel.add(buildInputGrid());
		controlPanel.add(Box.createVerticalStrut(8));
		controlPanel.add(buildH0Panel());
		controlPanel.add(Box.createVerticalStrut(8));
		controlPanel.add(buildSummaryPanel());
		controlPanel.add(Box.createVerticalStrut(12));

		btn_graph = new JButton("Generate Graph");
		btn_graph.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_graph.addActionListener(e -> generateGraph());
		controlPanel.add(btn_graph);

		controlPanel.add(Box.createVerticalStrut(6));

		btn_clear = new JButton("Clear All");
		btn_clear.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_clear.addActionListener(e -> clearAll());
		controlPanel.add(btn_clear);

		add(controlPanel, BorderLayout.WEST);

		attachDocumentListeners();

	}


	//Builds the initial height panel shown only in Type 3 problems.

	private JPanel buildH0Panel()

	{

		pnl_h0 = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
		pnl_h0.setBorder(BorderFactory.createTitledBorder("Initial Height (Type 3)"));
		pnl_h0.add(new JLabel("hâ (m):"));
		txt_h0 = new JTextField(8);
		pnl_h0.add(txt_h0);
		pnl_h0.add(new JLabel("  â launch height above ground"));
		pnl_h0.setVisible(false);
		return(pnl_h0);

	}


	//Builds the grid of X and Y axis input fields.

	private JPanel buildInputGrid()

	{

		JPanel grid = new JPanel(new GridBagLayout());
		grid.setBorder(BorderFactory.createTitledBorder("Kinematics Input (X / Y)"));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4, 4, 4, 4);
		c.fill   = GridBagConstraints.HORIZONTAL;

		txt_v1x = new JTextField(8); txt_v1y = new JTextField(8);
		txt_v2x = new JTextField(8); txt_v2y = new JTextField(8);
		txt_ax  = new JTextField(8); txt_ay  = new JTextField(8);
		txt_dx  = new JTextField(8); txt_dy  = new JTextField(8);
		txt_tx  = new JTextField(8); txt_ty  = new JTextField(8);

		txt_ay.setText("-9.81");

		String[] rowLabels = {"vâ (m/s)", "vâ (m/s)", "a (m/sÂ²)", "Îd (m)", "Ît (s)"};
		JTextField[][] pairs =
		{
			{txt_v1x, txt_v1y}, {txt_v2x, txt_v2y},
			{txt_ax,  txt_ay},  {txt_dx,  txt_dy},
			{txt_tx,  txt_ty}
		};

		c.gridx = 0; c.gridy = 0; c.weightx = 0.2;
		grid.add(new JLabel("Variable"), c);
		c.gridx = 1; c.weightx = 0.4;
		grid.add(new JLabel("X-Axis", SwingConstants.CENTER), c);
		c.gridx = 2;
		grid.add(new JLabel("Y-Axis", SwingConstants.CENTER), c);

		for (int i = 0; i < rowLabels.length; i++)
		{
			c.gridx = 0; c.gridy = i + 1; c.weightx = 0.2;
			grid.add(new JLabel(rowLabels[i]), c);
			c.gridx = 1; c.weightx = 0.4;
			grid.add(pairs[i][0], c);
			c.gridx = 2;
			grid.add(pairs[i][1], c);
		}

		return(grid);

	}


	//Builds the summary panel showing max height and total range after solving.

	private JPanel buildSummaryPanel()

	{

		JPanel summaryPanel = new JPanel(new GridLayout(2, 1, 4, 4));
		summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
		lbl_maxHeight  = new JLabel("Max Height (Y_max):  â");
		lbl_totalRange = new JLabel("Total Range (ÎdX):   â");
		summaryPanel.add(lbl_maxHeight);
		summaryPanel.add(lbl_totalRange);
		return(summaryPanel);

	}


	//Attaches a document listener to every input field so the solver runs on each keystroke.

	private void attachDocumentListeners()

	{

		JTextField[] allFields =
		{
			txt_v1x, txt_v1y, txt_v2x, txt_v2y,
			txt_ax,  txt_ay,  txt_dx,  txt_dy,
			txt_tx,  txt_ty,  txt_h0
		};

		for (JTextField f : allFields)
		{
			f.getDocument().addDocumentListener(new DocumentListener()
			{
				public void insertUpdate(DocumentEvent e)  { onFieldChanged(); }
				public void removeUpdate(DocumentEvent e)  { onFieldChanged(); }
				public void changedUpdate(DocumentEvent e) { onFieldChanged(); }
			});
		}

	}


	//Creates the graph panel and adds it to the centre of the frame.

	private void buildGraphPanel()

	{

		graphPanel = new GraphPanel();
		graphPanel.setBorder(BorderFactory.createTitledBorder("Trajectory Graph"));
		add(graphPanel, BorderLayout.CENTER);

	}


	//Unlocks all input fields so they are white and editable.

	private void unlockAll()

	{

		JTextField[] all =
		{
			txt_v1x, txt_v1y, txt_v2x, txt_v2y,
			txt_ax,  txt_ay,  txt_dx,  txt_dy,
			txt_tx,  txt_ty
		};

		for (JTextField f : all)
		{
			f.setEditable(true);
			f.setBackground(Color.WHITE);
		}

	}


	//Locks a field grey and non-editable and sets its text to the given value.

	private void lock(JTextField f, String value)

	{

		f.setEditable(false);
		f.setBackground(new Color(220, 220, 220));
		if (value != null) f.setText(value);

	}


	//Applies Type 1 rules: locks v1y to 0, ax to 0, and ay to -9.81.

	private void applyType1Rules()

	{

		clearAll();
		unlockAll();
		pnl_h0.setVisible(false);
		controlPanel.revalidate();
		lock(txt_v1y, "0.0");
		lock(txt_ax,  "0.0");
		lock(txt_ay,  "-9.81");

	}


	//Applies Type 2 rules: locks dy to 0, ax to 0, and ay to -9.81.

	private void applyType2Rules()

	{

		clearAll();
		unlockAll();
		pnl_h0.setVisible(false);
		controlPanel.revalidate();
		lock(txt_dy, "0.0");
		lock(txt_ax, "0.0");
		lock(txt_ay, "-9.81");

	}


	//Applies Type 3 rules: locks ax to 0 and ay to -9.81 and shows the h0 panel.

	private void applyType3Rules()

	{

		clearAll();
		unlockAll();
		pnl_h0.setVisible(true);
		controlPanel.revalidate();
		lock(txt_ax, "0.0");
		lock(txt_ay, "-9.81");

	}


	//Clears all green auto-filled fields so stale solved values do not affect the next solve.

	private void resetCalculatedFields()

	{

		Color green = new Color(198, 239, 206);
		JTextField[] all =
		{
			txt_v1x, txt_v1y, txt_v2x, txt_v2y,
			txt_ax,  txt_ay,  txt_dx,  txt_dy,
			txt_tx,  txt_ty,  txt_h0
		};

		for (JTextField f : all)
		{
			if (f != null && !f.isEditable() && f.getBackground().equals(green))
			{
				f.setText("");
				f.setEditable(true);
				f.setBackground(Color.WHITE);
			}
		}

	}


	//Runs the solver whenever a field changes and fills in any solved unknowns.

	private void onFieldChanged()

	{

		if (updatingFields) return;

		updatingFields = true;
		resetCalculatedFields();
		updatingFields = false;

		Double parsedDy = parse(txt_dy);
		if (rb_type3 != null && rb_type3.isSelected())
		{
			Double h0 = parse(txt_h0);
			if (h0 != null && txt_dy.getText().trim().isEmpty())
				parsedDy = -h0;
		}

		KinematicsSolver solver = new KinematicsSolver(
			parse(txt_v1x), parse(txt_v1y),
			parse(txt_v2x), parse(txt_v2y),
			parse(txt_ax),  parse(txt_ay),
			parse(txt_dx),  parsedDy,
			parse(txt_tx),  parse(txt_ty)
		);
		solver.solve();
		lastSolver = solver;

		updatingFields = true;
		setIfSolved(txt_v1x, solver.v1x);
		setIfSolved(txt_v1y, solver.v1y);
		setIfSolved(txt_v2x, solver.v2x);
		setIfSolved(txt_v2y, solver.v2y);
		setIfSolved(txt_ax,  solver.ax);
		setIfSolved(txt_ay,  solver.ay);
		setIfSolved(txt_dx,  solver.dx);
		setIfSolved(txt_dy,  solver.dy);
		setIfSolved(txt_tx,  solver.tx);
		setIfSolved(txt_ty,  solver.ty);

		if (rb_type3 != null && rb_type3.isSelected() && solver.dy != null)
		{
			if (txt_h0.getText().trim().isEmpty())
			{
				txt_h0.setText(fmt(-solver.dy));
				txt_h0.setBackground(new Color(198, 239, 206));
			}
		}

		updateSummaryLabels(solver);
		updatingFields = false;

	}


	//Draws the trajectory graph from the last solved state when the button is clicked.

	private void generateGraph()

	{

		if (lastSolver == null)
		{
			graphPanel.setSolvedValues(Double.NaN, Double.NaN, -9.81,
			                           Double.NaN, Double.NaN, Double.NaN, 0);
			graphPanel.repaint();
			return;
		}

		double v1x = (lastSolver.v1x != null) ? lastSolver.v1x : Double.NaN;
		double v1y = (lastSolver.v1y != null) ? lastSolver.v1y : Double.NaN;
		double ay  = (lastSolver.ay  != null) ? lastSolver.ay  : -9.81;
		double dx  = (lastSolver.dx  != null) ? lastSolver.dx  : Double.NaN;
		double dy  = (lastSolver.dy  != null) ? lastSolver.dy  : Double.NaN;
		double dt  = (lastSolver.tx  != null) ? lastSolver.tx  : Double.NaN;

		double launchY = 0;
		if (rb_type3 != null && rb_type3.isSelected())
		{
			Double h0 = parse(txt_h0);
			if (h0 != null)
				launchY = h0;
			else if (lastSolver.dy != null)
				launchY = -lastSolver.dy;
		}

		graphPanel.setSolvedValues(v1x, v1y, ay, dx, dy, dt, launchY);
		graphPanel.repaint();

	}


	//Fills a blank editable field with a solved value and colours it green.

	private void setIfSolved(JTextField f, Double val)

	{

		if (val == null) return;
		if (f.isEditable() && f.getText().trim().isEmpty())
		{
			f.setText(fmt(val));
			f.setBackground(new Color(198, 239, 206));
			f.setEditable(false);
		}

	}


	//Updates the max height and total range labels after a solve.

	private void updateSummaryLabels(KinematicsSolver solver)

	{

		if (!Double.isNaN(solver.ymax) && solver.ymax >= 0)
			lbl_maxHeight.setText(String.format("Max Height (Y_max):  %.3f m", solver.ymax));
		else
			lbl_maxHeight.setText("Max Height (Y_max):  â");

		if (solver.dx != null)
			lbl_totalRange.setText(String.format("Total Range (ÎdX):   %.3f m", solver.dx));
		else
			lbl_totalRange.setText("Total Range (ÎdX):   â");

	}


	//Clears all fields and reapplies the type locks, summary labels, and graph.

	private void clearAll()

	{

		updatingFields = true;

		JTextField[] clearable =
		{
			txt_v1x, txt_v1y, txt_v2x, txt_v2y,
			txt_dx,  txt_dy,  txt_tx,  txt_ty,  txt_h0
		};

		for (JTextField f : clearable)
		{
			if (f == null) continue;
			f.setEditable(true);
			f.setBackground(Color.WHITE);
			f.setText("");
		}

		if (rb_type1 != null && rb_type1.isSelected())
		{
			lock(txt_v1y, "0.0"); lock(txt_ax, "0.0"); lock(txt_ay, "-9.81");
		}
		else if (rb_type2 != null && rb_type2.isSelected())
		{
			lock(txt_dy, "0.0");  lock(txt_ax, "0.0"); lock(txt_ay, "-9.81");
		}
		else
		{
			lock(txt_ax, "0.0");  lock(txt_ay, "-9.81");
		}

		lbl_maxHeight.setText("Max Height (Y_max):  â");
		lbl_totalRange.setText("Total Range (ÎdX):   â");
		updatingFields = false;

		if (graphPanel != null)
		{
			graphPanel.setSolvedValues(Double.NaN, Double.NaN, -9.81,
			                           Double.NaN, Double.NaN, Double.NaN, 0);
			graphPanel.repaint();
		}

	}


	//Parses a double from a text field, returning null if the field is empty or invalid.

	private Double parse(JTextField f)

	{

		try
		{
			return(Double.parseDouble(f.getText().trim()));
		}
		catch (NumberFormatException e)
		{
			return(null);
		}

	}


	//Formats a double as a string, omitting unnecessary trailing zeros.

	private String fmt(double v)

	{

		if (v == Math.floor(v) && !Double.isInfinite(v)) return(String.valueOf((long) v));
		return(String.format("%.4f", v).replaceAll("0+$", "").replaceAll("\\.$", ".0"));

	}


	//Launches the application on the Swing event dispatch thread.

	public static void main(String[] args)

	{

		SwingUtilities.invokeLater(() ->
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (Exception ignored) {}
			new ProjectileCalculator();
		});

	}

}