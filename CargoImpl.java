// Full Name (StudentNum), Full Name (StudentNum)

/**
 * An implementation of the Cargo problem from the 2022 CITS2200 Project
 */
public class CargoImpl implements Cargo {
  /**
   * {@inheritdoc}
   */
  int t[] = new int[4 * 9];

  public int[] departureMasses(int stops, Query[] queries) {
    int[] masses = new int[stops];
    int[] result = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      Query query = queries[i];
      for (int j = query.collect; j < query.deliver; j++)
        masses[j] += query.cargoMass;
      result[i] = masses[query.collect];
    }
    return result;
  }

  void build(int a[], int v, int tl, int tr) {
    if (tl == tr) {
      this.t[v] = a[tl];
    } else {
      int tm = (tl + tr) / 2;
      build(a, v * 2, tl, tm);
      build(a, v * 2 + 1, tm + 1, tr);
      this.t[v] = this.t[v * 2] + this.t[v * 2 + 1];
    }
  }

  void update(int v, int tl, int tr, int pos, int new_val) {
    if (tl == tr) {
      this.t[v] = new_val;
    } else {
      int tm = (tl + tr) / 2;
      if (pos <= tm)
        update(v * 2, tl, tm, pos, new_val);
      else
        update(v * 2 + 1, tm + 1, tr, pos, new_val);
      this.t[v] = this.t[v * 2] + this.t[v * 2 + 1];
    }
  }

  int sum(int v, int tl, int tr, int l, int r) {
    if (l > r)
      return 0;
    if (l == tl && r == tr) {
      return this.t[v];
    }
    int tm = (tl + tr) / 2;
    return sum(v * 2, tl, tm, l, Math.min(r, tm))
        + sum(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
  }

  public static void main(String[] args) {

    int[] array = { 0, 0, 4, 0, 0, -4, 0, 0, 0 };

    CargoImpl hey = new CargoImpl();
    hey.build(array, 0, 1, array.length - 1);

    int hey2 = 3;

  }
}