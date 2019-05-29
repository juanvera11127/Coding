using System.Collections;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    Rigidbody2D rb;
	Animator anim;
	SpriteRenderer mySprite;
	public GameObject seedToDrop;
	[Header("Stats")]
    public float radius = 3f;
	public float speed = 2;
	public int health = 3;
	public Spawner mySpawner;

	private bool isNear;
	private bool canSee = true;
	private bool isAlive = true;
	private bool canMove = true;
	private bool following;
	private Vector3 wanderTarget;
	private float repositionTimer = 0;
    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
		anim = GetComponent<Animator>();
		mySprite = GetComponent<SpriteRenderer>();
    }

    
    void Update()
    {
		//Destroy enemy if offscreen
		if (!mySprite.isVisible)
		{
			if (mySpawner != null)
				mySpawner.spawnedEnemies -= 1;
			Destroy(gameObject);
		}

		//detect proximity
		if (Vector3.Distance(MCP.player.transform.position, transform.position) < radius)
			isNear = true;
		else
			isNear = false;
		//detect Line of Sight


		//keep sprite order proper
		if (transform.position.y > MCP.player.transform.position.y)
			mySprite.sortingOrder = 4;
		else if (transform.position.y < MCP.player.transform.position.y)
			mySprite.sortingOrder = 6;
		//sync animation with facing direction
		anim.SetFloat("Y", rb.velocity.y);

		if (canMove)
			MoveManager();

		if(health <= 0 && isAlive)
		{
			isAlive = false;
			StartCoroutine(Die());
		}
    }

	void MoveManager()
	{
		//move at a normalized speed towards player if they are near and visible (could remove normalized to have them go faster the farther they are, and slower as they get close. good for a friendly mode, as they wont push you.)
		if(isNear && canSee)
		{
			wanderTarget = (MCP.player.transform.position - transform.position).normalized * speed;
			following = true;
		}
		//otherwise wander towards a random point within +- 1 x and y of current position every 3 seconds
		else
		{
			repositionTimer += Time.deltaTime;
			if (following)
			{
				following = false;
				wanderTarget = Vector3.zero;
			}
			if(repositionTimer > 3)
			{
				repositionTimer = 0;
				wanderTarget = (new Vector3(transform.position.x + Random.Range(-1f, 1f), transform.position.y + Random.Range(-1f, 1f), 0) - transform.position) * speed;
			}
		}
		rb.velocity = wanderTarget;
	}
    private void OnTriggerEnter2D(Collider2D collision)
    {
		if (collision.gameObject.tag == "PlayerAtk")
		{
			StartCoroutine(GetHit());
			health -= 1;
		}
	}
	IEnumerator GetHit()
	{
		canMove = false;
		rb.velocity = (transform.position - MCP.player.transform.position).normalized * 2.5f;
		anim.SetTrigger("Hurt");
		yield return new WaitForSeconds(0.30f);
		rb.velocity = Vector3.zero;
		yield return new WaitForSeconds(0.25f);
		canMove = true;
	}
    
	IEnumerator Die()
	{
		radius = 0; speed = 0;
		if(mySpawner != null)
			mySpawner.spawnedEnemies -= 1;
		for (float i = 1; i > 0; i -= 0.01f)
		{
			anim.SetTrigger("Hurt");
			GetComponent<BoxCollider2D>().enabled = false;
			Color newColor = mySprite.color;
			newColor.a = i;
			mySprite.color = newColor;
			yield return new WaitForSeconds(0.01f);
		}
		for (int count = Random.Range(1, 4); count > 0; count -= 1)
		{
			Instantiate(seedToDrop, transform.position + new Vector3(Random.Range(-0.1f,0.1f), Random.Range(-0.1f, 0.1f),0), transform.rotation);
		}
		Destroy(gameObject, 0.1f);
	}
}
