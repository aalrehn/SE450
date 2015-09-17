package basics.mutabledata;
// Do not override equals or hashCode for mutable data.
// Do not make mutable data Comparable.
final public class MutPair<S,T>
implements Cloneable
{
	private S x;
	private T y;
	public MutPair() { }
	public MutPair(S x, T y) { this.x = x; this.y = y; }
	public void setFirst(S x) { this.x = x; }
	public void setSecond(T y) { this.y = y; }
	public S first() { return x; }
	public T second() { return y; }
	public String toString() { return "MutPair(" + x + "," + y + ")"; }
	// Clone does not play nice with generics because it returns Object.
	// Deep clone is difficult to implement generically because:
	// (1) cast to MutPair is allowed, not to MutPair<S,T>; we need the
	//     latter in order to get at the fields.
	// (2) Cloneable type does not have public clone method, so we
	//     cannot call x.clone() or y.clone(); this would be true even
	//     if we had "MutPair<S extends Cloneable,T extends Cloneable>"
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
