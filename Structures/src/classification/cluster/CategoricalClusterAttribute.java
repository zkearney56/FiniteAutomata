package classification.cluster;

import java.util.Iterator;

import list.ArrayList;

public class CategoricalClusterAttribute extends ClusterAttribute{
	private ArrayList<CategoryNode> dataNodes;


	public CategoricalClusterAttribute(ArrayList<Object> data){
		super(data);
		pullStrength = 1;
		dataNodes = new ArrayList<CategoryNode>();
		calculatePoints(data);
	}
	
	private void calculatePoints(ArrayList<Object> data){
		
		dataNodes = new ArrayList<CategoryNode>();
		Iterator<Object>itr = data.iterator();
		while(itr.hasNext()){
			boolean found = false;
			String base = (String)itr.next();
			if(!base.equals("NULL")){
			for(int i = 0; i < dataNodes.size(); i++){
				CategoryNode currNode = dataNodes.get(i);
				if(base.equals(currNode.getName())){
					currNode.increment();
					found = true;
					break;
				}
			}
			if(!found){
				dataNodes.add(new CategoryNode(base));
			}
		}
		}
		for(int i = 0; i < dataNodes.size(); i++){
			dataNodes.get(i).setPull(dataNodes.get(i).getCount()/data.size());
		}
	}
	
	class CategoryNode {

		private String name;
		private int count;
		private double pull = 0;

		public CategoryNode(String name) {
			this.name = name;
			count = 1;
		}

		public CategoryNode(CategoryNode point) {
			this.name = point.name;
			this.count = point.count;
		}

		public void increment() {
			count++;
		}

		public int getCount() {
			return count;
		}

		public String getName() {
			return name;
		}
		
		public void setPull(double pull){
			this.pull = pull;
		}
		public double pull(){
			return pull;
		}
}

	@Override
	public double calculatePull(Object val) {
		String curr = (String) val;
		if(curr.equals("NULL")){
			return 0;
		}
		for(int i = 0; i < dataNodes.size(); i++){
			CategoryNode node = dataNodes.get(i);
			if(curr.equals(node.getName())){
				return node.pull();
			}
		}
		return -10;
	}
}
