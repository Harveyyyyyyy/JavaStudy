package CompareArray;
import java.util.Arrays;
import java.util.Comparator;
public class CompareArray {
	public static <T extends java.lang.Comparable<? super T>> void sort(T[] value) {
		sort(value,true);
	}
	public static <T extends java.lang.Comparable<? super T>> void sort(T[] value,boolean asc) {
		for(int i=1;i<value.length;i++) {
			T x=value[i];
			int j=i-1;
			while(j>=0&&(asc?x.compareTo(value[j])<0:x.compareTo(value[j])>0))
				value[j+1]=value[j--];
			value[j+1]=x;
		}
	}
	public static <T> int binarySearch(T[] value,T key,Comparator<? super T> c) {
		return binarySearch(value,0,value.length-1,key,c);
	}
	public static <T> int binarySearch(T[] value,int begin,int end,T key,Comparator<? super T> c) {
		if(key!=null) {
			while(begin<=end) {
				int mid=(begin+end)/2;
				if(c.compare(key, value[mid])==0)
					return mid;
				if(c.compare(key, value[mid])<0)
					end=mid-1;
				else
					begin=mid+1;
			}
		}
		return -1;
	}
}
