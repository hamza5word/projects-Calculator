import java.util.Vector;


// CLASS THAT HANDLES SIMPLE ARITHMETIC EXPRESSIONS CALCULATIONS

public class Calculate {
	final static String NUMBER_EXP = "[0-9]+(.[0-9]*)?";
	final static String OPERT_EXP = "[+-x/!%]"; 
	private String exp;
	private Vector<Double> numbers = new Vector<>();
	private Vector<String> operators = new Vector<>();
	private boolean verificator = true;
	private double result;
	
	public Calculate() {
		this("");
	}
	
	public Calculate(String expression) {
		this.exp = expression;
		collect_data();
	}

	private long factorial(int n) {
		if(n <= 2) return n;
		else return n*factorial(n-1);
	}
	
	
	private void collect_data() {
		if(exp.length() == 0) return;
		// INITIALIZE NEEDED VARIABLES
		String tmp, collector = "";
		boolean special_op = false;
		// FIRST CHARACTER DISCOVERY
		char fc = exp.charAt(0);
		boolean done = false;
		int sign = 0;
		// LOOP THOUGHT THE EXPRESSION CHARACTER BY CHARACTER
		for(int i=0; i<exp.length(); i++) {
			// TEMP VARIABLE WILL TAKE THE CHARACTER NUMBER i IN THE EXPRESSION
			tmp = "" + exp.charAt(i);
			System.out.println("tmp["+i+"]="+tmp);
			// TEST IF THE CHARACTER IS A VALID NUMBER
			if(tmp.matches(NUMBER_EXP) || tmp.equalsIgnoreCase(".")) collector += tmp;
			// TEST FOR SCIENTIFIC FONCTIONS
			// COMPLICATED EXPRESSION
			else if(tmp.contains("(")) {
				try {
					int j = 1;
					char current = exp.charAt(i+j);
					String sub_exp = "";
					while(current != ')') {
						sub_exp += current;
						current = exp.charAt(i+(++j));
					}
					Calculate c = new Calculate(sub_exp);
					c.calculate();
					numbers.add(c.getResult());
					special_op = true;
					i += j;
				} catch (Exception e) {}
			}
			// EXP
			else if(tmp.contains("e")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					Calculate c = new Calculate(number);
					c.calculate();
					double output  = c.getResult();
					numbers.add(Math.floor(Math.exp(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// LOG
			else if(tmp.contains("l")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.log(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// RAC
			else if(tmp.contains("r")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.sqrt(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// COS
			else if(tmp.contains("c")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.cos(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// SIN
			else if(tmp.contains("s")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.sin(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// TAN
			else if(tmp.contains("t")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.tan(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// ABS
			else if(tmp.contains("a")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(Math.abs(output)*1000)/1000);
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// FLOOR
			else if(tmp.contains("f")) {
				try {
					// CALCUL THE NUMBER LENGTH
					int length = 0, j=i;
					char current = 0;
					while(current != ')') {
						length++;
						current = exp.charAt(++j);
					}
					length -= 4;
					String number = exp.substring(i+4, i+4+length);
					double output  = Double.parseDouble(number);
					numbers.add(Math.floor(output));
					special_op = true;
					i += 4 + length;
				} catch (Exception e) {}
			}
			// TEST IF THE CHARACTER IS A VALID OPERATOR
			else if(tmp.matches(OPERT_EXP)) {
				try {
					if((fc == '-' || fc == '+') && !done) {
						collector = "" + exp.charAt(i+1);
						int j=1;
						while(collector.matches(NUMBER_EXP)) {
							try {
								collector += exp.charAt(i+1+j);
								j++;
								if(collector.endsWith("+") || collector.endsWith("-") || collector.endsWith("x") || collector.endsWith("/")
										 || collector.endsWith("!") || collector.endsWith("%") || collector.endsWith("^")) {
									collector = collector.substring(0, collector.length()-1);
									break;
								}
							} catch (Exception e) {
								break;
							}
						}
						if(fc == '-') {
							numbers.add(-Double.parseDouble(collector));
							sign = 1;
						}
						if(fc == '+') {
							numbers.add(+Double.parseDouble(collector));
							sign = 2;
						}
						special_op = true;
						collector = "";
						done = true;
					}
					else if(tmp.equalsIgnoreCase("!")) {
						if(sign == 1) {
							numbers.add(-(double)factorial((int)Math.floor(Double.parseDouble(collector))));
							numbers.remove(0);
						}
						else if(sign == 2) {
							numbers.add((double)factorial((int)Math.floor(Double.parseDouble(collector))));
							numbers.remove(0);
						}
						else numbers.add((double)factorial((int)Math.floor(Double.parseDouble(collector))));
						special_op = true;
						collector = "";
						sign = 0;
					}
					else if(tmp.equalsIgnoreCase("%")) {
						if(sign == 1) {
							numbers.add(Double.parseDouble(collector)/100);
							numbers.remove(0);
						}
						else if(sign == 2) {
							numbers.add(Double.parseDouble(collector)/100);
							numbers.remove(0);
						}
						else numbers.add(Double.parseDouble(collector)/100);
						special_op = true;
						collector = "";
						sign = 0;
					}
					else {
						if(!special_op) numbers.add(Double.parseDouble(collector));
						collector = tmp;
						operators.add(collector);
						collector = "";
						special_op = false;
					}
				} catch (Exception e) {
					System.out.println("err1");
					this.verificator = false;
					this.numbers = null;
					this.operators = null;
					return;
				}
			}
			// SPECIAL CASES
			else if(tmp.equals(" "));
			else {
				System.out.println("err2");
				this.verificator = false;
				this.numbers = null;
				this.operators = null;
				return;
			}
		}
		try {
			if(!special_op) numbers.add(Double.parseDouble(collector));
		} catch (Exception e) {
			System.out.println("err3");
			this.verificator = false;
			this.numbers = null;
			this.operators = null;
			return;
		}
	}
	
	public boolean verify_exp() {
		return this.verificator;
	}
	
	public void calculate(String new_exp) {
		if(new_exp.length() > 0) {
			System.out.print("New Expression "+new_exp+" = ");
			exp = new_exp;
			clear_data();
			collect_data();
			calculate();
		}
	}
	
	private void clear_data() {
		numbers.clear();
		operators.clear();
	}
	
	public void calculate() {
		if(exp.length() == 0) return;
		if(this.verificator) {
			// TABLES THAT WILL STORE OPERATOR INDEXES
			Vector<Integer> d = new Vector<>();
			Vector<Integer> m = new Vector<>();
			Vector<Integer> s = new Vector<>();
			Vector<Integer> a = new Vector<>();
			Vector<Integer> p = new Vector<>();
			// MANAGING EXTRA VALUES
			Vector<Double> extra = new Vector<>();
			Vector<String> extra2 = new Vector<>();
			// POW
			//System.out.println("POW PREP : "+numbers);
			//System.out.println("OPER : "+operators);
			for(int i=0; i<operators.size(); i++) if(operators.get(i).equals("^")) p.add(i);
			for(int i=0; i<p.size(); i++) {
				numbers.set(p.get(i)+1, Math.pow(numbers.get(p.get(i)), numbers.get(p.get(i)+1)));
				extra.add(numbers.get(p.get(i)));
				extra2.add(operators.get(p.get(i)));
			}
			for(Double n : extra) numbers.remove(n);
			for(String n : extra2) operators.remove(n);
			extra.clear();
			extra2.clear();
			// DEV
			//System.out.println("DEV PREP : "+numbers);
			//System.out.println("OPER : "+operators);
			for(int i=0; i<operators.size(); i++) if(operators.get(i).equals("/")) d.add(i);
			for(int i=0; i<d.size(); i++) {
				numbers.set(d.get(i)+1, (numbers.get(d.get(i)) / numbers.get(d.get(i)+1)));
				extra.add(numbers.get(d.get(i)));
				extra2.add(operators.get(d.get(i)));
			}
			for(Double n : extra) numbers.remove(n);
			for(String n : extra2) operators.remove(n);
			extra.clear();
			extra2.clear();
			// MULT
			//System.out.println("MUL PREP : "+numbers);
			//System.out.println("OPER : "+operators);
			for(int i=0; i<operators.size(); i++) if(operators.get(i).equals("x")) m.add(i);
			for(int i=0; i<m.size(); i++) {
				numbers.set(m.get(i)+1, (numbers.get(m.get(i)) * numbers.get(m.get(i)+1)));
				extra.add(numbers.get(m.get(i)));
				extra2.add(operators.get(m.get(i)));
			}
			for(Double n : extra) numbers.remove(n);
			for(String n : extra2) operators.remove(n);
			extra.clear();
			extra2.clear();
			// SUB
			//System.out.println("SUB PREP : "+numbers);
			//System.out.println("OPER : "+operators);
			for(int i=0; i<operators.size(); i++) if(operators.get(i).equals("-")) s.add(i);
			for(int i=0; i<s.size(); i++) {
				numbers.set(s.get(i)+1, (numbers.get(s.get(i)) - numbers.get(s.get(i)+1)));
				extra.add(numbers.get(s.get(i)));
				extra2.add(operators.get(s.get(i)));
			}
			for(Double n : extra) numbers.remove(n);
			for(String n : extra2) operators.remove(n);
			extra.clear();
			extra2.clear();
			// ADD
			//System.out.println("ADD PREP : "+numbers);
			//System.out.println("OPER : "+operators);
			for(int i=0; i<operators.size(); i++) if(operators.get(i).equals("+")) a.add(i);
			for(int i=0; i<a.size(); i++) {
				numbers.set(a.get(i)+1, (numbers.get(a.get(i)) + numbers.get(a.get(i)+1)));
				extra.add(numbers.get(a.get(i)));
				extra2.add(operators.get(a.get(i)));
			}
			for(Double n : extra) numbers.remove(n);
			for(String n : extra2) operators.remove(n);
			extra.clear();
			extra2.clear();
			// Storing Result
			this.result = numbers.get(0);
			clear_data();
		} else {
			System.err.println("NOT A VALID EXPRESSION");
		}
	}

	public double getResult() {
		return this.result;
	}
	
	public static void main(String[] args) {
		Calculate c = new Calculate("5x3-1x4");
		c.calculate();
		System.out.println(c.getResult());
	}

}
