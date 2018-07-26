
public class CheckDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[] x={23,32,11,56};

int max=x[0];
int index=0;
for(int j=1;j<x.length;j++)
{
	if(x[index]<=x[j]){
		max=x[j];
		index=j;
		j++;
	}
	else{
		j++;
	}
}
System.out.println("The max of array value"+max+" At index: "+index);
	}

}
