using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FairyTrigger : MonoBehaviour
{
	public GameObject dialogueBox;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

	private void OnTriggerEnter2D(Collider2D other)
	{
		if(other.gameObject.tag == "Player")
		{
			dialogueBox.SetActive(true);
			MCP.player.GetComponent<PlayerControls>().canControl = false;
			GetComponent<Collider2D>().enabled = false;
		}
	}
}
