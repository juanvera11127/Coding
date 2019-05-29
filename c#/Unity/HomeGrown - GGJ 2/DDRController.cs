using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class DDRController : MonoBehaviour
{
	[Header("values")]
	public float speed = 1;
	public float frequency = 1;
	public int BPM = 120;
	public float duration = 30;
	public float score = 0;

	[HideInInspector]
	public int progressToAdd;
	public bool tut;

	[Header("References")]
	public GameObject arrowPrefab;
	public Transform spawnTarget;
	public Transform barScaler;
	public GameObject danceMu;
	public AudioClip[] songs;
	public AudioClip successSound, failSound;
	public GameObject failDialogue, successDialogue;
	private AudioSource mySound;
	public string nameToBuild;
	[Header("Debug")]
	public bool run;

    // Start is called before the first frame update
    void Awake()
    {
		MCP.DDRC = this;
		gameObject.SetActive(false);
		mySound = GetComponent<AudioSource>();
    }

	public void StartDDR(int whichSong)
	{
		MCP.player.GetComponent<PlayerControls>().canControl = false;
		MCP.player.SetActive(false);
		danceMu.SetActive(true);
		mySound.clip = songs[whichSong];
		StartCoroutine(SpawnArrows());
	}
    // Update is called once per frame
    void Update()
    {
		barScaler.transform.localScale = new Vector3(Mathf.Clamp((score * 2) * 0.01f,0,1), 1, 1);
		if (run)
		{
			run = false;
			StartDDR(Random.Range(0,songs.Length));
		}
    }

	IEnumerator SpawnArrows()
	{
		MCP.player.SetActive(false);
		mySound.Play();
		for (float i = duration; i > 0;)
		{
			MCP.player.SetActive(false);
			i -= 0.5f * frequency;
			DDRGenerator lastSpawn =  Instantiate(arrowPrefab, spawnTarget).GetComponent<DDRGenerator>();
			lastSpawn.transform.localPosition = new Vector2(0, -26);
			lastSpawn.speed = speed;
			lastSpawn.muAnim = danceMu.GetComponent<Animator>();
			yield return new WaitForSeconds(0.5f * frequency);
		}
		yield return new WaitForSeconds(10);
		if (!tut)
		{
			if (score >= 50)
			{
				print("success");
				MCP.mcp.progress += progressToAdd;
				MCP.mcp.GrewAThing(nameToBuild);
				MCP.mcp.soundBox.clip = successSound;
				MCP.mcp.soundBox.Play();
			}
			else
			{
				MCP.mcp.soundBox.clip = failSound;
				MCP.mcp.soundBox.Play();
				print("failiure");
			}
			score = 0;
			gameObject.SetActive(false);
		}
		MCP.player.GetComponent<PlayerControls>().canControl = true;
		MCP.player.SetActive(true);
		danceMu.SetActive(false);
		if (tut)
		{
			if (score >= 50)
			{
				successDialogue.SetActive(true);
				MCP.mcp.GetComponent<TutorialController>().GrowHouse1();
				MCP.mcp.soundBox.clip = successSound;
				MCP.mcp.soundBox.Play();
				tut = false;
				score = 0;
				gameObject.SetActive(false);
			}
			else
			{
				MCP.mcp.soundBox.clip = failSound;
				MCP.mcp.soundBox.Play();
				score = 0;
				failDialogue.SetActive(true);
			}
		}
	}
}
