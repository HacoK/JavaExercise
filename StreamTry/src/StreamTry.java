import java.util.List;

import com.google.common.collect.Lists;

public class StreamTry {
	public static void main(String[] args){
		List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
		System.out.println("sum is:"+nums.stream().filter(num -> num != null).
		distinct().mapToInt(num -> num * 2).
		peek(System.out::println).skip(2).limit(4).sum());
		
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
		System.out.println(ints.stream().allMatch(item -> item < 100));
		ints.stream().max((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);
		
		List<Integer> Ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("Ints sum is:"+ Ints.stream().reduce((sum, item) -> sum + item).get());
	}
}
