using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MCP : MonoBehaviour
{
    public static MCP mcp;
	public static GameObject player;
	public static DDRController DDRC;
	public static CameraShake cameraShake;
	public AudioSource soundBox;
	public GameObject muDig;
	public Text progressTracker;
	public GameObject[] cameras;
    public float woodSeeds, stoneSeeds, metalSeeds, waterSeeds;
    public int playerHealth = 15;
	public GameObject[] propPrefabs;
	public GameObject[] enemyPrefabs;
	public AudioClip doorSound;
	public Transform houseTarget;

    public Text woodSeedsText, stoneSeedsText, metalSeedsText, waterSeedsText;
	public GameObject smallHouse;
	public GameObject bigHouse;
	public int progress;
	public int progressNeeded;
	public GameObject cameraBoi;
    // Start is called before the first frame update
    void Awake()
    {
        mcp = this;
    }

    // Update is called once per frame
    void Update()
    {
        woodSeedsText.text = "x" + woodSeeds;
        stoneSeedsText.text = "x" + stoneSeeds;
        metalSeedsText.text = "x" + metalSeeds;
        waterSeedsText.text = "x" + waterSeeds;
		progressTracker.text = "Progress: " + progress + " / " + progressNeeded;
		if(progress >= progressNeeded && smallHouse.activeInHierarchy)
		{
			smallHouse.SetActive(false);
			bigHouse.SetActive(true);
		}
		
    }

	public void GrewAThing(string what)
	{
		soundBox.clip = doorSound;
		soundBox.Play();
		foreach (GameObject cam in cameras)
		{
			cam.SetActive(false);
		}
		cameraBoi.SetActive(true);
		player.transform.position = houseTarget.transform.position;

		switch (what)
		{
			case "Bed":
				Instantiate(propPrefabs[0], player.transform.position, transform.rotation);
				break;
			case "Chair":
				Instantiate(propPrefabs[1], player.transform.position, transform.rotation);
				break;
			case "Couch":
				Instantiate(propPrefabs[2], player.transform.position, transform.rotation);
				break;
			case "Lamp":
				Instantiate(propPrefabs[3], player.transform.position, transform.rotation);
				break;
			case "Fridge":
				Instantiate(propPrefabs[4], player.transform.position, transform.rotation);
				break;
			case "Sink":
				Instantiate(propPrefabs[5], player.transform.position, transform.rotation);
				break;
			case "Stove":
				Instantiate(propPrefabs[6], player.transform.position, transform.rotation);
				break;
			case "Table":
				Instantiate(propPrefabs[7], player.transform.position, transform.rotation);
				break;
			case "Throne":
				Instantiate(propPrefabs[8], player.transform.position, transform.rotation);
				break;
			case "TV":
				Instantiate(propPrefabs[9], player.transform.position, transform.rotation);
				break;
			case "Wardrobe":
				Instantiate(propPrefabs[10], player.transform.position, transform.rotation);
				break;
			case "FairyStatue":
				Instantiate(propPrefabs[11], player.transform.position, transform.rotation);
				break;

		}
	}
}
