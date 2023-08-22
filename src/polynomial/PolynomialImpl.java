package polynomial;

public class PolynomialImpl implements Polynomial {

    private static class Node {

        private int coefficient;
        private int power;
        private Node next;

        private Node (int coefficient, int power) {

            this.coefficient = coefficient;
            this.power = power;
            this.next = null;

        }

    }

    private Node head;

    /**
     * Creates an instance of the PolynomialImpl class
     */
    public PolynomialImpl() {

        this.head = null;

    }

    /**
     * Given the user's input, creates an instance of the PolynomialImpl class
     * @param polynomial the polynomial to be created
     * @throws IllegalArgumentException if the polynomial passed in is not formatted correctly
     */
    public PolynomialImpl(String polynomial) throws IllegalArgumentException {

        PolynomialImpl result = new PolynomialImpl();

        if (polynomial.equals("")) {

            this.head = result.head;

        } else {

            String[] terms = polynomial.split(" ");
            int coefficient;
            int power;

            for (String s : terms) {

                if (!(s.matches("\\d+x\\^\\d+") || s.matches("-\\d+x\\^\\d+") ||
                        s.matches("\\d+") || s.matches("-\\d+") ||
                        s.matches("\\+\\d+x\\^\\d+") || s.matches("\\+\\d+"))) {

                    throw new IllegalArgumentException("String must be formatted correctly");

                }

                s = s.replaceAll("\\+","");

                String[] cp = s.split("x\\^");

                if (cp.length == 1) {

                    if (!cp[0].equals("0")) {

                        coefficient = Integer.parseInt(cp[0]);
                        power = 0;
                        result.addTerm(coefficient, power);
                    }

                } else {

                    if (!cp[0].equals("0")) {

                        coefficient = Integer.parseInt(cp[0]);
                        power = Integer.parseInt(cp[1]);
                        result.addTerm(coefficient, power);

                    }

                }

            }

            this.head = result.head;

        }

    }

    @Override
    public String toString() {

        Node current = head;
        StringBuilder result = new StringBuilder();

        if (current == null) {

            result.append("0");
            return result.toString();
        }

        while (current != null) {

            if (current.coefficient == 0 && current.next == null) {

                result.append("0");

            } else {

                if (current.next == null) {

                    if (current.power != 0) {

                        result.append(current.coefficient).append("x").append("^").append(current.power);

                    } else {

                        result.append(current.coefficient);

                    }

                } else if (current.next.coefficient > 0) {

                    if (current.power != 0) {

                        result.append(current.coefficient).append("x").append("^").append(current.power).append(" ").append("+");

                    } else {

                        result.append(current.coefficient).append(" ").append("+");

                    }

                } else {

                    if (current.coefficient != 0 && current.power != 0) {

                        result.append(current.coefficient).append("x").append("^").append(current.power).append(" ");

                    } else {

                        if (current.coefficient != 0) {

                            result.append(current.coefficient).append(" ");

                        }

                    }

                }

            }

            current = current.next;

        }

        return result.toString();

    }


    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {

        if (!(other instanceof PolynomialImpl)) {

            throw new IllegalArgumentException("The other polynomial must be of type PolynomialImpl");

        }

        PolynomialImpl p2 = (PolynomialImpl) other;
        Node otherPointer = p2.head;

        Node currentPointer = this.head;

        Polynomial result = new PolynomialImpl();

        while (currentPointer != null || otherPointer != null) {

            if (currentPointer == null) {

                while (otherPointer != null) {

                    result.addTerm(otherPointer.coefficient, otherPointer.power);
                    otherPointer = otherPointer.next;
                }

            } else if (otherPointer == null) {

                while (currentPointer != null) {

                    result.addTerm(currentPointer.coefficient, currentPointer.power);
                    currentPointer = currentPointer.next;
                }

            } else {

                if (currentPointer.power > otherPointer.power) {

                    result.addTerm(currentPointer.coefficient, currentPointer.power);
                    currentPointer = currentPointer.next;

                } else if (currentPointer.power == otherPointer.power) {

                    result.addTerm(currentPointer.coefficient + otherPointer.coefficient,
                            currentPointer.power);
                    currentPointer = currentPointer.next;
                    otherPointer = otherPointer.next;

                } else {

                    result.addTerm(otherPointer.coefficient, otherPointer.power);
                    otherPointer = otherPointer.next;

                }

            }

        }

        return result;
    }

    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {

        if (power < 0) {

            throw new IllegalArgumentException("Power must be greater than or equal to zero");

        } else {

            Node newNode = new Node(coefficient, power);

            if (head == null) {

                head = newNode;

            } else {

                Node current = head;

                if (current.power > newNode.power) {

                    while (current.next != null && current.next.power > newNode.power) {
                        current = current.next;
                    }

                    if (current.next == null) {

                        current.next = newNode;

                    } else if (current.next.power == newNode.power) {

                        current.next.coefficient += newNode.coefficient;

                    } else {

                        newNode.next = current.next;
                        current.next = newNode;

                    }

                } else if (current.power == newNode.power) {

                    current.coefficient += newNode.coefficient;

                } else {

                    newNode.next = current;
                    head = newNode;

                }

            }

        }

    }

    @Override
    public boolean isSame(Polynomial poly) {

        if (!(poly instanceof PolynomialImpl)) {

            return false;
        } else {

            return this.toString().equals(poly.toString());
        }

    }

    @Override
    public double evaluate(double x) {

        Node current = head;
        double sum = 0.0;

        while (current != null) {

            sum += current.coefficient * Math.pow(x, current.power);
            current = current.next;

        }

        return sum;

    }

    @Override
    public int getCoefficient(int power) {

        Node current = head;

        while (current != null) {

            if (current.power == power) {

                return current.coefficient;
            } else {

                current = current.next;

            }

        }

        return 0;

    }

    @Override
    public int getDegree() {

        if (head != null) {

            return head.power;
        } else {

            return 0;
        }

    }

}
