using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FishingTrigger : MonoBehaviour
{
	public bool playerInTrigger;
	public PlayerControls.facingDir requiredDirection;
	private PlayerControls playerControl;
	private Animator playerAnim;
	private bool fishing;
	private float fishingTimer = 0;
	private bool bite;
    // Start is called before the first frame update
    void Start()
    {
		playerControl = MCP.player.GetComponent<PlayerControls>();
		playerAnim = MCP.player.GetComponent<Animator>();
	}


	IEnumerator StopFishing()
	{
		playerAnim.SetTrigger("Reel");
		fishing = false;
		fishingTimer = 0;
		yield return new WaitForSeconds(2);
		playerControl.canControl = true;

	}
	IEnumerator CatchFish()
	{
		playerAnim.SetTrigger("Reel");
		fishing = false;
		fishingTimer = 0;
		GameObject lastSpawn = Instantiate(MCP.mcp.enemyPrefabs[0], MCP.player.transform);
		lastSpawn.GetComponent<Animator>().applyRootMotion = false;
		yield return new WaitForSeconds(0.5f);
		lastSpawn.GetComponent<Animator>().applyRootMotion = true;
		lastSpawn.transform.parent = null;
		lastSpawn.transform.position = playerAnim.transform.position + new Vector3(0, 1, 0);
		playerControl.canControl = true;
	}
    // Update is called once per frame
    void Update()
    {
		if (fishing)
		{
			fishingTimer += Time.deltaTime;
			if (Input.GetButtonDown("Swing"))
			{
				if (bite)
				{
					StartCoroutine(CatchFish());
				}
				else
				{
					StartCoroutine(StopFishing());
				}
			}
		}
		else if (!fishing && playerInTrigger && Input.GetButtonDown("Swing") && playerControl.facing == requiredDirection)
			StartFishing();

		if(fishingTimer >= 1)
		{
			fishingTimer = 0;
			bite = false;
			if(!bite && Random.Range(0,100) < 10)
			{
				bite = true;
				playerAnim.SetTrigger("Bite");
			}
		}
    }

	void StartFishing()
	{
		playerControl.canControl = false;
		playerAnim.SetTrigger("Fish");
		fishing = true;
	}

	private void OnTriggerEnter2D(Collider2D collision)
	{
		if(collision.gameObject.tag == "Player"){
			playerInTrigger = true;
		}
	}
	private void OnTriggerExit2D(Collider2D collision)
	{
		if (collision.gameObject.tag == "Player")
		{
			playerInTrigger = false;
		}
	}
}
