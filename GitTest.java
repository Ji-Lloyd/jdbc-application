public class GitTest {
    private String name;
    private String address;
    private int age;

    public GitTest(){

    }

    public GitTest(String name){

    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GitTest [name=" + name + ", address=" + address + ", age=" + age + "]";
    }

    

    

}
