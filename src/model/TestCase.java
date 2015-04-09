package model;

public class TestCase {
	
	private int id;
	private Priority priority;
	private Probability probability;
	private Effect effect;
	private Context context;
	private String caseName;
	private String goal;
	private Model model;
	private RCUType rcuType;
	private ModelConstraint modelConstraint;
	private double timeExecution;
	private Consequence consequence;
	private Risk risk;
	private double fitnessGrA;
	

	

	public double getFitnessGrA() {
		return fitnessGrA;
	}

	public void setFitnessGrA(double fitnessGrA) {
		this.fitnessGrA = fitnessGrA;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public double getePriority() {
		return ePriority;
	}

	public void setePriority(double ePriority) {
		this.ePriority = ePriority;
	}

	public double geteProbability() {
		return eProbability;
	}

	public void seteProbability(double eProbability) {
		this.eProbability = eProbability;
	}

	public double geteConsequence() {
		return eConsequence;
	}

	public void seteConsequence(double eConsequence) {
		this.eConsequence = eConsequence;
	}

	private double ePriority;
	private double eProbability;
	private double eConsequence;
	

	public Consequence getConsequence() {
		return consequence;
	}

	public void setConsequence(Consequence consequence) {
		this.consequence = consequence;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Probability getProbability() {
		return probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public RCUType getRcuType() {
		return rcuType;
	}

	public void setRcuType(RCUType rcuType) {
		this.rcuType = rcuType;
	}

	public ModelConstraint getModelConstraint() {
		return modelConstraint;
	}

	public void setModelConstraint(ModelConstraint modelConstraint) {
		this.modelConstraint = modelConstraint;
	}

	public double getTimeExecution() {
		return timeExecution;
	}

	public void setTimeExecution(double timeExecution) {
		this.timeExecution = timeExecution;
	}

}
